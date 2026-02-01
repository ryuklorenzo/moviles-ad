package ies.sequeros.dam.pmdp.aplicacion.categorias

import ies.sequeros.dam.pmdp.aplicacion.categorias.commands.UpdateCategoriaCommand
import ies.sequeros.dam.pmdp.aplicacion.categorias.dtos.CategoriaDto
import ies.sequeros.dam.pmdp.modelo.Categoria
import ies.sequeros.dam.pmdp.modelo.ICategoriaRepositorio

class UpdateCategoriaUseCase(
    private val repository: ICategoriaRepositorio
) {
    suspend operator fun invoke(command: UpdateCategoriaCommand) {
        val exists = repository.existsById(command.id)
        if (!exists) {
            throw NoSuchElementException("Categoria no encontrada")
        }

        val categoria = Categoria(
            id = command.id,
            nombre = command.nombre,
            descripcion = command.descripcion,
            activo = command.activo
        )

        repository.update(categoria)
    }
}