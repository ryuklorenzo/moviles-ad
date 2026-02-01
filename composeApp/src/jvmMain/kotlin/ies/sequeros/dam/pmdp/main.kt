package ies.sequeros.dam.pmdp

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import ies.sequeros.dam.pmdp.di.moduloAplicacion

import ies.sequeros.dam.pmdp.di.moduloDominio
import ies.sequeros.dam.pmdp.di.moduloInfraestructuran
import ies.sequeros.dam.pmdp.di.moduloPresentacion

import org.koin.core.context.GlobalContext.startKoin

fun main() = application {
    startKoin {
        modules(moduloInfraestructuran,moduloDominio,moduloAplicacion, moduloPresentacion)
    }
    Window(
        onCloseRequest = ::exitApplication,
        title = "DigiRest",
    ) {
        App()
    }
}