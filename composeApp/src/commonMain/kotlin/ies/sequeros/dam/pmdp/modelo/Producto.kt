package ies.sequeros.dam.pmdp.modelo

import kotlinx.serialization.Serializable

@Serializable
class Producto(
    var id: String,
    var nombre: String,
    var descripcion: String,
    var categoriaId: String,
    var precio: Float,
    var activo: Boolean
) {
}