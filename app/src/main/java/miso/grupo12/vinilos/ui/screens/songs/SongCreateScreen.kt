package miso.grupo12.vinilos.ui.screens.songs

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import miso.grupo12.vinilos.data.MockData
import miso.grupo12.vinilos.ui.components.PageForm
import miso.grupo12.vinilos.ui.components.form.SelectInput
import miso.grupo12.vinilos.ui.components.form.TextInput

@Composable
fun SongCreateScreen() {
    var name by remember { mutableStateOf("") }
    var duration by remember { mutableStateOf("") }
    var albumId by remember { mutableStateOf("") }

    PageForm("Agregar Canción") {
        TextInput(
            value = name,
            onChange = { name = it },
            label = "Nombre"
        )

        TextInput(
            value = duration,
            onChange = { duration = it },
            label = "Duración"
        )

        SelectInput(
            value = albumId,
            onSelect = { albumId = it },
            label = "Álbum",
            options = MockData.albums.map { it.title }
        )
    }
}
