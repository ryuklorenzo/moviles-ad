package ies.sequeros.dam.pmdp.aplicacion.productos.dtos

data class CategoriaProductoDto(
    val id: String,
    val nombre: String,
    val descripcion: String,
    val activo: Boolean
) {
}