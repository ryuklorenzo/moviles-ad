package ies.sequeros.dam.pmdp.aplicacion.categorias.commands

import androidx.compose.ui.graphics.drawscope.Stroke

data class AddCategoriaCommand (
    val nombre: String,
    val descripcion: String,
    val activo: Boolean
)