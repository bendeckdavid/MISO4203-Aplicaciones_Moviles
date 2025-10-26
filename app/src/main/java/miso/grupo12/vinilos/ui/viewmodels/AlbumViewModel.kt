package miso.grupo12.vinilos.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import miso.grupo12.vinilos.data.api.RetrofitInstance
import miso.grupo12.vinilos.data.models.Album
import miso.grupo12.vinilos.data.repository.AlbumRepository

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
            } catch (e: Exception) {
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