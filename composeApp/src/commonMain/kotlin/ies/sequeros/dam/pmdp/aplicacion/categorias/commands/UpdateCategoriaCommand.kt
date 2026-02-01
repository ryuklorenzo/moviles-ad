package ies.sequeros.dam.pmdp.aplicacion.categorias.commands

import kotlinx.serialization.Serializable

@Serializable
data class UpdateCategoriaCommand (
    val id: String,
    val nombre: String,
    val descripcion: String,
    val activo: Boolean

)