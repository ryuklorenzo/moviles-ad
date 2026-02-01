package ies.sequeros.dam.pmdp.aplicacion.productos

import ies.sequeros.dam.pmdp.aplicacion.productos.dtos.ProductoDto
import ies.sequeros.dam.pmdp.modelo.IProductoRepositorio

class GetProductoUseCase(
    private val repository: IProductoRepositorio
) {
    suspend operator fun invoke(id: String): ProductoDto {
        val producto = repository.findById(id)
            ?: throw NoSuchElementException("Producto no encontrado con id $id")

        return ProductoDto(
            id = producto.id,
            nombre = producto.nombre,
            descripcion = producto.descripcion,
            categoriaId = producto.categoriaId,
            precio = producto.precio,
            activo = producto.activo
        )

    }

}