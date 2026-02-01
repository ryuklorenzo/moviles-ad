package ies.sequeros.dam.pmdp.aplicacion.categorias

import ies.sequeros.dam.pmdp.modelo.ICategoriaRepositorio

class DeleteCategoriaUseCase(
    private val repository: ICategoriaRepositorio
) {
    suspend operator fun invoke(id: String) {
        val exists = repository.existsById(id)
        if (!exists) {
            throw NoSuchElementException("Categoria no encontrada")
        }
        repository.delete(id)
    }
}