package miso.grupo12.vinilos.ui.components

import androidx.compose.runtime.Composable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import miso.grupo12.vinilos.data.SectionInfo
import miso.grupo12.vinilos.ui.common.LocalNavigator

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun SectionDetails(section: SectionInfo, content: @Composable ColumnScope.() -> Unit) {
    val route = LocalNavigator.current
    val scrollState = rememberScrollState()

    Scaffold(Modifier.fillMaxSize(), topBar = {
        TopAppBar(title = { Text(section.title) }, navigationIcon = {
            IconButton(onClick = { route.back() }) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = "Volver"
                )
            }
        })
    }) { innerPadding ->

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(innerPadding)
                .verticalScroll(scrollState)
                .padding(26.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            AsyncImage(
                model = section.imageUrl,
                contentDescription = section.title,
                modifier = Modifier
                    .size(240.dp)
                    .clip(RoundedCornerShape(10.dp))
            )

            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(12.dp),
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
                Text(
                    text = "Este álbum captura un viaje sonoro que fusiona ritmos contemporáneos con melodías clásicas. La esencia de la experimentación musical, llevando al oyente por paisajes sonoros únicos. Cada pista cuenta una historia diferente, creando una experiencia auditiva cohesiva y memorable.\n",
                    style = MaterialTheme.typography.labelSmall,
                    textAlign = TextAlign.Justify,
                    modifier = Modifier.padding(top = 20.dp)
                )

                content()
            }
        }
    }
}
