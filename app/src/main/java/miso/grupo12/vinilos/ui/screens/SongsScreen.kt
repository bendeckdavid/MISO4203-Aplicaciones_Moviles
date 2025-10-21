package miso.grupo12.vinilos.ui.screens

import androidx.compose.runtime.Composable
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import miso.grupo12.vinilos.data.MockData
import miso.grupo12.vinilos.ui.components.AddButton
import miso.grupo12.vinilos.ui.components.SectionList

@Composable
fun SongsScreen() {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        floatingActionButton = { AddButton("song_create") }
    ) { innerPadding ->
        SectionList(
            title = "Listado de Canciones",
            route = "song",
            sections = MockData.songs,
            modifier = Modifier.padding(innerPadding)
        )
    }
}
