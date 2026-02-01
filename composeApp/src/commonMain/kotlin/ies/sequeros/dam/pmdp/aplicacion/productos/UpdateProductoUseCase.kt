package ies.sequeros.dam.pmdp.aplicacion.productos

import ies.sequeros.dam.pmdp.aplicacion.productos.commands.UpdateProductoCommand
import ies.sequeros.dam.pmdp.modelo.IProductoRepositorio
import ies.sequeros.dam.pmdp.modelo.Producto

class UpdateProductoUseCase(
    private val repository: IProductoRepositorio
) {
    suspend operator fun invoke(command: UpdateProductoCommand) {
        val exists = repository.existsById(command.id)
        if (!exists) {
            throw NoSuchElementException("Producto no encontrado")
        }

        val producto = Producto(
            id = command.id,
            nombre = command.nombre,
            descripcion = command.descripcion,
            categoriaId = command.categoriaId,
            precio = command.precio,
            activo = command.activo
        )

        repository.update(producto)
    }

}