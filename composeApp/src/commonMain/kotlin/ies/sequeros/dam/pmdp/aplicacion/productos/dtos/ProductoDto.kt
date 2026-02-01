package ies.sequeros.dam.pmdp.aplicacion.productos.dtos

data class ProductoDto(
    val id: String,
    val nombre: String,
    val descripcion: String,
    val categoriaId: String,
    val precio: Float,
    val activo: Boolean
) {
}