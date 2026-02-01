package ies.sequeros.dam.pmdp.aplicacion.productos

import ies.sequeros.dam.pmdp.modelo.IProductoRepositorio

class DeleteProductoUseCase(
    private val repository: IProductoRepositorio
) {
    suspend operator fun invoke(id: String) {
        val exists = repository.existsById(id)
        if (!exists) {
            throw NoSuchElementException("Producto no encontrado")
        }
        repository.delete(id)
    }

}