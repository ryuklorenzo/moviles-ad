package ies.sequeros.dam.pmdp

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import ies.sequeros.dam.pmdp.di.moduloAplicacion
import ies.sequeros.dam.pmdp.di.moduloDominio
import ies.sequeros.dam.pmdp.di.moduloInfraestructura
import ies.sequeros.dam.pmdp.di.moduloPresentacion
import ies.sequeros.dam.pmdp.vista.categorias.CategoriasScreen
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.KoinApplication

@Composable
@Preview
fun App() {
    val appModules = listOf(
        moduloDominio,
        moduloInfraestructura,
        moduloAplicacion,
        moduloPresentacion
    )

    KoinApplication(application = {
        modules(appModules)
    }) {
        MaterialTheme {
            Surface(
                modifier = Modifier.fillMaxSize(),
                color = MaterialTheme.colorScheme.background
            ) {
                CategoriasScreen()
            }
        }
    }
}