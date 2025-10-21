package miso.grupo12.vinilos.ui.common

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.compositionLocalOf
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import miso.grupo12.vinilos.ui.screens.AlbumCreateScreen
import miso.grupo12.vinilos.ui.screens.ArtistCreateScreen
import miso.grupo12.vinilos.ui.screens.SongCreateScreen
import miso.grupo12.vinilos.ui.screens.AlbumDetailsScreen
import miso.grupo12.vinilos.ui.screens.ArtistDetailsScreen
import miso.grupo12.vinilos.ui.screens.SongDetailsScreen
import miso.grupo12.vinilos.ui.theme.slideInLeft
import miso.grupo12.vinilos.ui.theme.slideOutLeft

data class Navigator(
    val navigate: (String) -> Unit, val back: () -> Unit
)

val LocalNavigator = compositionLocalOf<Navigator> {
    error("LocalNavigator not provided. Ensure AppNavigator wraps your Composable tree.")
}

@Composable
fun AppNavigator() {
    val navController = rememberNavController()

    CompositionLocalProvider(
        LocalNavigator provides Navigator(
            navigate = { route -> navController.navigate(route) },
            back = { navController.popBackStack() })
    ) {
        NavHost(navController = navController, startDestination = "home") {
            composable("home") { NavigatorBar() }
            animatedRoute("album_create") { AlbumCreateScreen() }
            animatedRoute("artist_create") { ArtistCreateScreen() }
            animatedRoute("song_create") { SongCreateScreen() }
            animatedRoute("album_details/{modelId}") { modelId -> AlbumDetailsScreen(modelId) }
            animatedRoute("artist_details/{modelId}") { modelId -> ArtistDetailsScreen(modelId) }
            animatedRoute("song_details/{modelId}") { modelId -> SongDetailsScreen(modelId) }
        }
    }
}

fun NavGraphBuilder.animatedRoute(
    route: String, content: @Composable (String) -> Unit
) {
    composable(
        route = route, enterTransition = slideInLeft, exitTransition = slideOutLeft
    ) { backStackEntry ->
        val modelId = backStackEntry.arguments?.getString("modelId") ?: ""
        content(modelId)
    }
}
