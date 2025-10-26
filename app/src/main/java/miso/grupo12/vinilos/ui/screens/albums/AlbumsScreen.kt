package miso.grupo12.vinilos.ui.screens.albums

import androidx.compose.runtime.Composable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import miso.grupo12.vinilos.ui.components.AddButton
import miso.grupo12.vinilos.ui.components.SectionList
import androidx.lifecycle.viewmodel.compose.viewModel
import miso.grupo12.vinilos.data.SectionInfo
import miso.grupo12.vinilos.ui.viewmodels.AlbumViewModel

@Composable
fun AlbumsScreen(viewModel: AlbumViewModel = viewModel()) {

    val albums by viewModel.albums.collectAsState()

    // Cargar datos una sola vez al crear la pantalla
    LaunchedEffect(Unit) {
        viewModel.loadAlbums()
    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        floatingActionButton = { AddButton("album_create") }
    ) { innerPadding ->
        when {
            albums.isEmpty() -> {
                // Mostrar progreso o mensaje vacío
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(innerPadding),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator( modifier = Modifier.testTag("progress_indicator"))
                }
            }

            else -> {
                // Adaptamos los datos del backend al modelo visual SectionInfo
                val sections = albums.map { album ->
                    SectionInfo(
                        id = album.id.toString(),
                        title = album.name,
                        subtitle = album.recordLabel,
                        metadata = album.genre,
                        imageUrl = album.cover
                    )
                }

                SectionList(
                    title = "Listado de Álbumes",
                    route = "album",
                    sections = sections,
                    modifier = Modifier.padding(innerPadding)
                )
            }
        }
    }
}
