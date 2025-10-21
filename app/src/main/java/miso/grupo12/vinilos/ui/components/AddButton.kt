package miso.grupo12.vinilos.ui.components

import androidx.compose.runtime.Composable
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import miso.grupo12.vinilos.ui.common.LocalNavigator

@Composable
fun AddButton(route: String) {
    val router = LocalNavigator.current

    FloatingActionButton(
        onClick = { router.navigate(route) },
        containerColor = MaterialTheme.colorScheme.primaryContainer,
        contentColor = MaterialTheme.colorScheme.onPrimaryContainer
    ) {
        Icon(
            imageVector = Icons.Filled.Add,
            contentDescription = "Agregar"
        )
    }
}
