package ies.sequeros.dam.pmdp.aplicacion.productos.dtos

data class ProductoWithCategoriasDto(
    val id: String,
    val nombre: String,
    val descripcion: String,
    val categoriaId: String,
    val precio: Float,
    val activo: Boolean,
    val categorias: List<CategoriaProductoDto>
) {
}