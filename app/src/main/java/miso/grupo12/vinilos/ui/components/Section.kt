package miso.grupo12.vinilos.ui.components

import androidx.compose.runtime.Composable
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import miso.grupo12.vinilos.data.SectionInfo

@Composable
fun Section(section: SectionInfo, details: () -> Unit) {

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { details() }
            .padding(16.dp),
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        AsyncImage(
            model = section.imageUrl,
            contentDescription = "Portada de ${section.title}",
            modifier = Modifier
                .size(120.dp)
                .clip(RoundedCornerShape(10.dp))
        )

        Column(
            verticalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier.padding(top = 4.dp)
        ) {
            Text(
                text = section.title,
                style = MaterialTheme.typography.titleMedium
            )
            Text(
                text = section.subtitle,
                style = MaterialTheme.typography.bodyMedium
            )
            Text(
                text = section.metadata,
                style = MaterialTheme.typography.labelSmall
            )
        }
    }
}
