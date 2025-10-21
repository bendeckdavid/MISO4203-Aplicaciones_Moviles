package miso.grupo12.vinilos.ui.screens

import androidx.compose.runtime.Composable
import miso.grupo12.vinilos.data.MockData
import miso.grupo12.vinilos.ui.components.SectionDetails

@Composable
fun ArtistDetailsScreen(artistId: String) {
    SectionDetails(MockData.artists.first { it.id == artistId }, {})
}
