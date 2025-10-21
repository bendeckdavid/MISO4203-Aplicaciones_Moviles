package miso.grupo12.vinilos.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import miso.grupo12.vinilos.data.MockData
import miso.grupo12.vinilos.ui.components.SectionDetails

@Composable
fun AlbumDetailsScreen(albumId: String) {
    val comments = MockData.comments.shuffled().take(4)

    SectionDetails(MockData.albums.first { it.id == albumId }, {

        Column(
            Modifier.fillMaxWidth().padding(top = 10.dp),
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.spacedBy(30.dp),
        ) {
            Text(
                text = "Comentarios",
                style = MaterialTheme.typography.titleMedium
            )

            comments.forEach { comment ->
                Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                    Text(
                        text = comment.authorName,
                        style = MaterialTheme.typography.bodyMedium
                    )
                    Text(
                        text = comment.comment,
                        style = MaterialTheme.typography.labelSmall,
                    )
                }
            }

        }
    })
}
