package ies.sequeros.dam.pmdp.aplicacion.productos.commands

data class AddProductoCommand(
    var id: String,
    var nombre: String,
    var descripcion: String,
    var categoriaId: String,
    var precio: Float,
    var activo: Boolean
) {
}