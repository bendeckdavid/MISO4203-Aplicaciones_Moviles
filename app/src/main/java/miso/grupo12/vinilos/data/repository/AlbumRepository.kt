package miso.grupo12.vinilos.data.repository

import miso.grupo12.vinilos.data.api.AlbumService
import miso.grupo12.vinilos.data.models.Album

class AlbumRepository(private val api: AlbumService) {

    suspend fun getAlbums(): List<Album> = api.getAlbums()

    suspend fun getAlbumById(id: Int): Album = api.getAlbumById(id)
}