package miso.grupo12.vinilos

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import dagger.hilt.android.AndroidEntryPoint
import miso.grupo12.vinilos.ui.common.AppNavigator
import miso.grupo12.vinilos.ui.theme.VinilosTheme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            VinilosTheme {
                AppNavigator()
            }
        }
    }
}

