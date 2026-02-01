package ies.sequeros.dam.pmdp.aplicacion.categorias.commands

import androidx.compose.ui.graphics.drawscope.Stroke
import kotlinx.serialization.Serializable

@Serializable
data class AddCategoriaCommand (
    val nombre: String,
    val descripcion: String,
    val activo: Boolean
)