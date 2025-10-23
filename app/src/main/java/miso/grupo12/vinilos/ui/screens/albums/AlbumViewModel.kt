package miso.grupo12.vinilos.ui.screens.albums

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import miso.grupo12.vinilos.data.models.Album
import miso.grupo12.vinilos.data.repository.AlbumRepository
import miso.grupo12.vinilos.data.api.RetrofitInstance

class AlbumViewModel : ViewModel() {

    private val repository = AlbumRepository(RetrofitInstance.albumService)

    private val _albums = MutableStateFlow<List<Album>>(emptyList())
    val albums: StateFlow<List<Album>> = _albums

    private val _selectedAlbum = MutableStateFlow<Album?>(null)
    val selectedAlbum: StateFlow<Album?> = _selectedAlbum

    fun loadAlbums() {
        viewModelScope.launch {
            try {
                _albums.value = repository.getAlbums()
                println("✅ Albums descargados: ${_albums.value.size}")
                //_albums.value = result
            } catch (e: Exception) {
                println("❌ Error al obtener álbumes: ${e.message}")
                e.printStackTrace()
            }
        }
    }

    fun loadAlbumById(id: Int) {
        viewModelScope.launch {
            try {
                _selectedAlbum.value = repository.getAlbumById(id)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}