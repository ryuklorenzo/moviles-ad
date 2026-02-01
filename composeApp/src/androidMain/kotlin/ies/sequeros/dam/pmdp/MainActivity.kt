package ies.sequeros.dam.pmdp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview

import ies.sequeros.dam.pmdp.di.moduloAplicacion
import ies.sequeros.dam.pmdp.di.moduloDominio
import ies.sequeros.dam.pmdp.di.moduloInfraestructuran
import ies.sequeros.dam.pmdp.di.moduloPresentacion

import org.koin.core.context.GlobalContext.startKoin

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)
        startKoin {
            modules(moduloInfraestructuran,moduloDominio,moduloAplicacion, moduloPresentacion)
        }
        setContent {
            App()
        }
    }
}

@Preview
@Composable
fun AppAndroidPreview() {
    App()
}