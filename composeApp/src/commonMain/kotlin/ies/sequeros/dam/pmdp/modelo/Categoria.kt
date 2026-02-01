package ies.sequeros.dam.pmdp.modelo

import kotlinx.serialization.Serializable

@Serializable
class Categoria(
    var id: String,
    var nombre: String,
    var descripcion: String,
    var activo: Boolean = false,
    private val _productos: MutableList<Producto> = mutableListOf()
) {
}