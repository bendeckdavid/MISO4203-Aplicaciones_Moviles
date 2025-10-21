package miso.grupo12.vinilos.ui.components

import androidx.compose.runtime.Composable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import miso.grupo12.vinilos.data.SectionInfo
import miso.grupo12.vinilos.ui.common.LocalNavigator

@Composable
fun SectionList(
    title: String,
    route: String,
    sections: List<SectionInfo>,
    modifier: Modifier = Modifier
) {
    val navigator = LocalNavigator.current

    LazyColumn(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(20.dp)
    ) {
        item {
            Text(
                text = title,
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier.padding(20.dp)
            )
        }
        items(
            items = sections,
            key = { section -> section.id }
        ) { section ->
            Section(
                section = section,
                details = { navigator.navigate(route + "_details/${section.id}") }
            )
        }
    }
}
