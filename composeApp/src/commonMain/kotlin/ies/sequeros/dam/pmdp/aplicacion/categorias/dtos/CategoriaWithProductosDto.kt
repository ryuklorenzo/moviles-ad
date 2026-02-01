package ies.sequeros.dam.pmdp.aplicacion.categorias.dtos

class CategoriaWithProductosDto (
    val id: String,
    val nombre: String,
    val descripcion: String,
    val activo: Boolean,
    val productos: List<ProductoCategoriaDto>
)