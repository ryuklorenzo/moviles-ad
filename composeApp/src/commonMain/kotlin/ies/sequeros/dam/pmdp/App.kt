package ies.sequeros.dam.pmdp


import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.*
import androidx.compose.ui.graphics.Color
import org.jetbrains.compose.ui.tooling.preview.Preview

import ies.sequeros.dam.pmdp.vista.componentes.ListadoDigimon


@Composable
@Preview
fun App() {
    //val repositorio: IDigimonRepositorio= RestDigimonRepositorio("https://digimon-api.vercel.app/api/digimon")
    //val digimonViewModel: DigimonViewModel= DigimonViewModel(repositorio)
    //estilo de la aplicacion
    MaterialTheme(
        colorScheme =lightColorScheme(
            primary = Color(0xFF006D3C),
            onPrimary = Color.White,
            secondary = Color(0xFF4CAF50),
            background = Color(0xFFF1FDF4),
        )
    ) {
        ListadoDigimon()
    }
}