package miso.grupo12.vinilos.ui.screens.songs

import androidx.compose.runtime.Composable
import miso.grupo12.vinilos.data.MockData
import miso.grupo12.vinilos.ui.components.SectionDetails

@Composable
fun SongDetailsScreen(songId: String) {
    SectionDetails(MockData.songs.first { it.id == songId }, {})
}
