package miso.grupo12.vinilos

import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import io.mockk.*
import miso.grupo12.vinilos.ui.common.LocalNavigator
import miso.grupo12.vinilos.ui.common.Navigator
import miso.grupo12.vinilos.ui.screens.albums.AlbumsScreen
import miso.grupo12.vinilos.ui.viewmodels.AlbumViewModel
import org.junit.*
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class AlbumsScreenTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    private lateinit var realViewModel: AlbumViewModel

    @Before
    fun setup() {
        // Crear mock del ViewModel
        realViewModel = AlbumViewModel()
    }

    @After
    fun tearDown() {
        unmockkAll()
    }

    @Test
    fun cuando_inicia_la_pantalla_se_renderiza_correctamente() {
        // When: se renderiza la pantalla
        composeTestRule.setContent {
            CompositionLocalProvider(
                LocalNavigator provides Navigator(
                    navigate = { },
                    back = { }
                )
            ) {
                AlbumsScreen(realViewModel)
            }
        }

        // Then: debe renderizarse sin crashes
        composeTestRule.waitForIdle()

        // Y debe mostrar contenido (progress o datos)
        val componentsExist = composeTestRule
            .onAllNodesWithTag("album_create")
            .fetchSemanticsNodes()
            .isNotEmpty()

        assert(componentsExist) {
            "El componente debe renderizarse correctamente"
        }
    }

    @Test
    fun cuando_carga_albums_desde_api_lista_contiene_elementos() {
        // When: se renderiza la pantalla
        composeTestRule.setContent {
            CompositionLocalProvider(
                LocalNavigator provides Navigator(
                    navigate = { },
                    back = { }
                )
            ) {
                AlbumsScreen(realViewModel)
            }
        }

        // Wait: espera máximo 10 segundos a que cargue
        composeTestRule.waitUntil(timeoutMillis = 10_000) {
            val noProgress = composeTestRule
                .onAllNodesWithTag("progress_indicator")
                .fetchSemanticsNodes()
                .isEmpty()

            val hasTitle = composeTestRule
                .onAllNodesWithText("Listado de Álbumes")
                .fetchSemanticsNodes()
                .isNotEmpty()

            noProgress && hasTitle
        }

        // Then: verifica que hay contenido en la lista
        composeTestRule.waitForIdle()

        // Cuenta todos los nodos clickables (serían los items de álbumes)
        val clickableNodes = composeTestRule
            .onAllNodes(hasClickAction())
            .fetchSemanticsNodes()

        // Debe haber al menos 2 (1 FAB + al menos 1 item de álbum)
        assert(clickableNodes.size >= 2) {
            "Debe haber al menos un álbum en la lista (encontrados: ${clickableNodes.size - 1} items + FAB)"
        }

        println("✅ Test exitoso: Se encontraron ${clickableNodes.size - 1} álbumes en la lista")
    }


}