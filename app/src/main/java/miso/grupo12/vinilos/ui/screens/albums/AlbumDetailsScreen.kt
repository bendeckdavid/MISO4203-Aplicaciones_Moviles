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
import androidx.lifecycle.viewmodel.compose.viewModel
import miso.grupo12.vinilos.data.SectionInfo
import miso.grupo12.vinilos.data.models.Album
import miso.grupo12.vinilos.ui.components.SectionDetails

@Composable
fun AlbumDetailsScreen(albumId: String,
                       viewModel: AlbumViewModel = viewModel()) {
    //val comments = MockData.comments.shuffled().take(4)
    val album by viewModel.selectedAlbum.collectAsState()
    LaunchedEffect(albumId) {
        viewModel.loadAlbumById(albumId.toInt())
    }

    if (album == null) {
        // Pantalla de carga
        Box(
            Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator()
        }
    } else {
        AlbumDetailContent(album!!)
    }
}

@Composable
fun AlbumDetailContent(album: Album) {

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
            Text(
                text = "Comentarios",
                style = MaterialTheme.typography.titleMedium
            )

            if (album.comments.isEmpty()) {
                Text("Este álbum aún no tiene comentarios.")
            } else {
                album.comments.forEach { comment ->
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
