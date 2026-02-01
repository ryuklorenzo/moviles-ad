package ies.sequeros.dam.pmdp.aplicacion.categorias

import ies.sequeros.dam.pmdp.aplicacion.categorias.commands.AddCategoriaCommand
import ies.sequeros.dam.pmdp.modelo.Categoria
import ies.sequeros.dam.pmdp.modelo.ICategoriaRepositorio

class AddCategoriaUseCase(
    private val repository: ICategoriaRepositorio
) {
    suspend operator fun invoke(command: AddCategoriaCommand) {
        val categoria = Categoria(
            id = "",
            nombre = command.nombre,
            descripcion = command.descripcion,
            activo = command.activo
        )
        repository.create(categoria)
    }
}