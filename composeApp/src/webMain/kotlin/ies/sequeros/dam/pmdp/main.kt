package ies.sequeros.dam.pmdp

import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.window.ComposeViewport
import ies.sequeros.dam.pmdp.di.moduloAplicacion
import ies.sequeros.dam.pmdp.di.moduloDominio
import ies.sequeros.dam.pmdp.di.moduloInfraestructuran
import ies.sequeros.dam.pmdp.di.moduloPresentacion

import org.koin.core.context.startKoin


@OptIn(ExperimentalComposeUiApi::class)
fun main() {
    startKoin {
            modules(moduloInfraestructuran,moduloDominio, moduloAplicacion, moduloPresentacion)

    }
    ComposeViewport {
        App()
    }
}