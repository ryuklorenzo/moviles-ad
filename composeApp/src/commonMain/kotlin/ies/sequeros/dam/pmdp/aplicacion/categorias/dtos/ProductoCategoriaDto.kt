package ies.sequeros.dam.pmdp.aplicacion.categorias.dtos

data class ProductoCategoriaDto (
    val id: String,
    val nombre: String,
    val descripcion: String,
    val activo: Boolean
)