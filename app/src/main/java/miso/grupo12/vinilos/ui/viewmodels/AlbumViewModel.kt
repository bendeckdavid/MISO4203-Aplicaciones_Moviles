package miso.grupo12.vinilos.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import miso.grupo12.vinilos.data.local.entities.AlbumEntity
import miso.grupo12.vinilos.data.local.relations.AlbumComplete
import miso.grupo12.vinilos.data.repository.AlbumRepository
import javax.inject.Inject

@HiltViewModel
class AlbumViewModel @Inject constructor(
    private val repository: AlbumRepository
) : ViewModel() {

    // ===============================================
    // 1. LISTADO DE ÁLBUMES (OFFLINE-FIRST)
    // ===============================================

    // El Repositorio retorna Flow<List<AlbumEntity>> (datos de Room)
    // Usamos stateIn para convertir el Flow en un StateFlow observable por la UI.
    val albums: StateFlow<List<AlbumEntity>> = repository.albums
        .catch {
            // Manejar errores si el Flow falla (ej: error fatal de DB)
            emit(emptyList())
        }
        .stateIn(
            scope = viewModelScope,
            // Empieza a colectar inmediatamente y mantiene el último valor.
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptyList()
        )

    // ===============================================
    // 2. DETALLE DEL ÁLBUM SELECCIONADO
    // ===============================================

    // Este StateFlow se actualizará cada vez que se llame a selectAlbumDetails
    private val _selectedAlbum = MutableStateFlow<AlbumComplete?>(null)
    val selectedAlbum: StateFlow<AlbumComplete?> = _selectedAlbum

    /**
     * Inicia la colección del Flow de detalle de álbum del Repositorio.
     * @param id El ID del álbum a consultar.
     */
    fun selectAlbumDetails(id: Int) {
        viewModelScope.launch {
            // El Repositorio retorna un Flow (getAlbumCompleteDetails)
            repository.getAlbumCompleteDetails(id)
                // Se colecta el Flow de Room continuamente
                .collect { albumComplete ->
                    // Se actualiza el StateFlow del ViewModel con el valor más reciente de Room
                    _selectedAlbum.value = albumComplete
                }
            // Si quieres forzar una actualización de la red, la harías aquí:
            // repository.fetchAndSaveAlbum(id)
        }
    }

    // NOTA: No necesitamos una función loadAlbums() separada,
    // ya que la colección se inicia automáticamente en la definición de 'albums'
    // y el Repositorio llama a refreshAlbums() en su onStart.
}