package miso.grupo12.vinilos.ui.common

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.adaptive.navigationsuite.NavigationSuiteScaffold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Album
import androidx.compose.material.icons.filled.MusicNote
import androidx.compose.material.icons.filled.Person
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import miso.grupo12.vinilos.ui.screens.AlbumsScreen
import miso.grupo12.vinilos.ui.screens.ArtistsScreen
import miso.grupo12.vinilos.ui.screens.SongsScreen

enum class AppDestinations(
    val label: String,
    val icon: ImageVector
) {
    ALBUMS("Albumes", Icons.Filled.Album),
    ARTISTS("Artistas", Icons.Filled.Person),
    TRACKS("Canciones", Icons.Filled.MusicNote)
}

@Composable
fun NavigatorBar() {
    var currentDestination by rememberSaveable { mutableStateOf(AppDestinations.ALBUMS) }

    NavigationSuiteScaffold(
        navigationSuiteItems = {
            AppDestinations.entries.forEach { destination ->
                item(
                    icon = {
                        Icon(
                            imageVector = destination.icon,
                            contentDescription = destination.label
                        )
                    },
                    label = { Text(destination.label) },
                    selected = destination == currentDestination,
                    onClick = { currentDestination = destination }
                )
            }
        }
    ) {
        Box(modifier = Modifier.padding(top = 20.dp)) {
            when (currentDestination) {
                AppDestinations.ALBUMS -> AlbumsScreen()
                AppDestinations.ARTISTS -> ArtistsScreen()
                AppDestinations.TRACKS -> SongsScreen()
            }
        }
    }
}
