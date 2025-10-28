package miso.grupo12.vinilos.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import kotlinx.coroutines.flow.Flow
import miso.grupo12.vinilos.data.local.entities.*
import miso.grupo12.vinilos.data.local.relations.AlbumComplete

@Dao
interface AlbumDao {

    // ===============================================
    // OPERACIONES DE INSERCIÓN (Para guardar datos de la API)
    // ===============================================

    /** Inserta un solo AlbumEntity. Reemplaza si ya existe (estrategia Upsert). */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAlbum(album: AlbumEntity)

    /** Inserta una lista de pistas. */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTracks(tracks: List<TrackEntity>)

    /** Inserta una lista de comentarios. */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertComments(comments: List<CommentEntity>)

    /** Inserta un artista. */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPerformer(performer: PerformerEntity)

    /** Inserta las referencias cruzadas N:N. */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAlbumPerformerCrossRefs(crossRefs: List<PerformerAlbumCrossRef>)

    // ===============================================
    // ORQUESTACIÓN DE GUARDADO (Para el Mapper)
    // ===============================================

    /**
     * Inserta un álbum y todos sus componentes relacionados en una sola transacción.
     * Esta es la función clave que usarás en tu Repositorio después de mapear el JSON.
     */
    @Transaction
    suspend fun insertAlbumAndComponents(
        album: AlbumEntity,
        tracks: List<TrackEntity>,
        comments: List<CommentEntity>,
        performers: List<PerformerEntity>,
        crossRefs: List<PerformerAlbumCrossRef>
    ) {
        insertAlbum(album)
        insertTracks(tracks)
        insertComments(comments)

        // Asumiendo que quieres guardar los artistas también
        // Puedes usar @Insert(onConflict = OnConflictStrategy.IGNORE) si no quieres actualizar
        performers.forEach { insertPerformer(it) }

        insertAlbumPerformerCrossRefs(crossRefs)
    }

    // ===============================================
    // OPERACIONES DE CONSULTA (Para la UI)
    // ===============================================

    /** * Consulta el listado de todos los álbumes.
     * Útil para la pantalla principal.
     */
    @Query("SELECT * FROM albums")
    fun getAlbums(): Flow<List<AlbumEntity>>

    /**
     * Consulta un álbum completo con todas sus relaciones anidadas.
     * **IMPORTANTE:** Requiere la anotación @Transaction para asegurar que las consultas
     * anidadas de las relaciones se ejecuten correctamente.
     */
    @Transaction
    @Query("SELECT * FROM albums WHERE id = :albumId")
    fun getAlbumCompleteDetails(albumId: Int): Flow<AlbumComplete>
}