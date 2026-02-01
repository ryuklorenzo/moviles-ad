package ies.sequeros.dam.pmdp.aplicacion.categorias.commands

data class UpdateCategoriaCommand (
    val id: String,
    val nombre: String,
    val descripcion: String,
    val activo: Boolean

)