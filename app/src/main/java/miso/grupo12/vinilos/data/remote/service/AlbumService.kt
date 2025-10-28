package miso.grupo12.vinilos.data.remote.service

import miso.grupo12.vinilos.data.remote.models.Album
import retrofit2.http.GET
import retrofit2.http.Path

interface AlbumService {

    // Obtener todos los álbumes
    @GET("albums")
    suspend fun getAlbums(): List<Album>

    // Obtener un álbum por ID
    @GET("albums/{id}")
    suspend fun getAlbumById(@Path("id") id: Int): Album
}