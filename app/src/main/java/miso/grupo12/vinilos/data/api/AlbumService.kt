package miso.grupo12.vinilos.data.api

import miso.grupo12.vinilos.data.models.Album
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