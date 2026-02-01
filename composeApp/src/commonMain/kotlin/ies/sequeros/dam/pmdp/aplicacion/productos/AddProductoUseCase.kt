package ies.sequeros.dam.pmdp.aplicacion.productos

import ies.sequeros.dam.pmdp.aplicacion.productos.commands.AddProductoCommand
import ies.sequeros.dam.pmdp.modelo.IProductoRepositorio
import ies.sequeros.dam.pmdp.modelo.Producto

class AddProductoUseCase(
    private val repository: IProductoRepositorio

) {
    suspend operator fun invoke(command: AddProductoCommand): Producto?{
        val producto = Producto(
            id = "",
            nombre = command.nombre,
            descripcion = command.descripcion,
            categoriaId = command.categoriaId,
            precio = command.precio.toFloat(),
            activo = command.activo
        )
        repository.create(producto)
        return null
    }
}