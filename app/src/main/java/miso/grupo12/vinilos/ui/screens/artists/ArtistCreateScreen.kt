package miso.grupo12.vinilos.ui.screens.artists

import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import miso.grupo12.vinilos.data.MockData
import miso.grupo12.vinilos.ui.components.PageForm
import miso.grupo12.vinilos.ui.components.form.DateInput
import miso.grupo12.vinilos.ui.components.form.SelectInput
import miso.grupo12.vinilos.ui.components.form.TextInput

@Composable
fun ArtistCreateScreen() {
    var image by remember { mutableStateOf("") }
    var name by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }
    var birthdate by remember { mutableStateOf("") }
    var genre by remember { mutableStateOf("") }

    PageForm("Agregar Álbum") {

        AsyncImage(
            model = "https://randomuser.me/api/portraits/men/71.jpg",
            contentDescription = "Foto de Artista",
            modifier = Modifier
                .size(200.dp)
                .clip(RoundedCornerShape(10.dp))
        )

        TextInput(
            value = image,
            onChange = { image = it },
            label = "Imagen"
        )

        TextInput(
            value = name,
            onChange = { name = it },
            label = "Nombre"
        )

        TextInput(
            value = description,
            onChange = { description = it },
            label = "Descripción"
        )

        DateInput(
            value = birthdate,
            onSelect = { birthdate = it },
            label = "Fecha Nacimiento"
        )

        SelectInput(
            value = genre,
            onSelect = { genre = it },
            label = "Género Principal",
            options = MockData.artists.map { it.subtitle }.distinct()
        )
    }
}
