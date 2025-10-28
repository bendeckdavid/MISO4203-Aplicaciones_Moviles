package miso.grupo12.vinilos.ui.screens.albums

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.material3.*
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import miso.grupo12.vinilos.data.SectionInfo
import miso.grupo12.vinilos.data.local.relations.AlbumComplete
import miso.grupo12.vinilos.data.remote.models.Album
import miso.grupo12.vinilos.ui.components.SectionDetails
import miso.grupo12.vinilos.ui.viewmodels.AlbumViewModel

@Composable
fun AlbumDetailsScreen(albumId: String,
                       viewModel: AlbumViewModel = hiltViewModel()
) {

    val albumComplete by viewModel.selectedAlbum.collectAsState()

    //val comments = MockData.comments.shuffled().take(4)
   // val album by viewModel.selectedAlbum.collectAsState()
    LaunchedEffect(albumId) {
        viewModel.selectAlbumDetails(albumId.toInt())
    }

    if (albumComplete == null) {
        // Pantalla de carga
        Box(
            Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator()
        }
    } else {
        AlbumDetailContent(albumComplete!!)
    }
}

@Composable
fun AlbumDetailContent(albumComplete: AlbumComplete) {

    val album = albumComplete.album
    val comments = albumComplete.comments
    val tracks = albumComplete.tracks
    val performers = albumComplete.performers

    val albumDetail = SectionInfo(
        id = album.id.toString(),
        title = album.name,
        subtitle = album.recordLabel ?: "Desconocido",
        metadata = "Lanzado en: ${album.releaseDate ?: "Fecha no disponible"}",
        imageUrl = album.cover
    )

    SectionDetails(albumDetail) {
        Column(
            Modifier
                .fillMaxWidth()
                .padding(top = 10.dp),
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.spacedBy(20.dp),
        ) {

            // --- Secciones adicionales (Pistas y Artistas) ---

            // 1. Artistas/Performers
            Text(text = "Artistas", style = MaterialTheme.typography.titleMedium)
            Text(
                text = performers.joinToString { it.name },
                style = MaterialTheme.typography.bodyMedium
            )

            // 2. Tracks/Pistas
            Text(text = "Pistas", style = MaterialTheme.typography.titleMedium)
            if (tracks.isEmpty()) {
                Text("Este álbum no tiene pistas listadas.")
            } else {
                tracks.forEach { track ->
                    Text(
                        text = "🎶 ${track.name} (${track.duration})",
                        style = MaterialTheme.typography.bodyMedium
                    )
                }
            }

            // --- Sección de Comentarios (Tu código original modificado) ---

            Text(
                text = "Comentarios",
                style = MaterialTheme.typography.titleMedium
            )

            if (comments.isEmpty()) {
                Text("Este álbum aún no tiene comentarios.")
            } else {
                comments.forEach { comment -> // Iterar sobre la lista de CommentEntity
                    Column(
                        verticalArrangement = Arrangement.spacedBy(6.dp),
                        modifier = Modifier.padding(bottom = 10.dp)
                    ) {
                        Text(
                            text = "⭐ ${comment.rating}/5",
                            style = MaterialTheme.typography.labelMedium
                        )
                        Text(
                            text = comment.description,
                            style = MaterialTheme.typography.bodyMedium
                        )
                    }
                }
            }
        }
    }
}
