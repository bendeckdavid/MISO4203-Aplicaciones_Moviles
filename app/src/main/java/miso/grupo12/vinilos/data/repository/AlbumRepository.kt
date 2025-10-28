package miso.grupo12.vinilos.data.repository

import miso.grupo12.vinilos.data.local.dao.AlbumDao
import miso.grupo12.vinilos.data.remote.service.AlbumService
import javax.inject.Inject
import javax.inject.Singleton
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart
import miso.grupo12.vinilos.data.local.entities.*
import miso.grupo12.vinilos.data.local.relations.AlbumComplete
import miso.grupo12.vinilos.data.mappers.toRoomComponents

@Singleton
class AlbumRepository @Inject constructor(
    private val api: AlbumService,
    private val dao: AlbumDao
) {

    // 1. Flujo de datos: Obtiene entidades planas de Room (AlbumEntity)
    val albums: Flow<List<AlbumEntity>> = dao.getAlbums()
        .onStart { refreshAlbums() } // Intenta sincronizar al inicio
        .catch { emit(emptyList()) } // Si falla la DB o el refresh, devuelve lista vacía

    // 2. Refresca datos: Llama API, Mapea, Guarda en transacciones de Room
    suspend fun refreshAlbums() {
        try {
            val remoteAlbums = api.getAlbums() // List<AlbumApiResponse>

            remoteAlbums.forEach { apiAlbum ->
                val components = apiAlbum.toRoomComponents() // Uso del Mapper

                // Uso de la función transaccional del DAO
                dao.insertAlbumAndComponents(
                    album = components.albumEntity,
                    tracks = components.trackEntities,
                    comments = components.commentEntities,
                    performers = components.performerEntities.distinctBy { it.id },
                    crossRefs = components.crossRefs
                )
            }
        } catch (e: Exception) {
            // Error de red, no afecta a Room.
            // Es buena práctica loguear el error (ej: Log.e(...))
        }
    }

    // 3. Obtener un álbum completo con relaciones (retorna el POJO de Room)
    fun getAlbumCompleteDetails(id: Int): Flow<AlbumComplete?> { // El retorno es un POJO de Room
        // El DAO retorna un Flow del POJO de Room. Room maneja la búsqueda de relaciones.
        // Si el Flow emite null, podrías iniciar un refresh, pero aquí solo se consulta.
        return dao.getAlbumCompleteDetails(id)
    }
}