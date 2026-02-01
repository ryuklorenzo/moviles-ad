package ies.sequeros.dam.pmdp.aplicacion.productos

import ies.sequeros.dam.pmdp.modelo.IProductoRepositorio
import ies.sequeros.dam.pmdp.modelo.Producto

class GetAllProductosUseCase(
    private val productoRepositorio: IProductoRepositorio
) {
    suspend fun invoke(): List<Producto>{
        return productoRepositorio.all()
    }
}