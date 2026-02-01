package ies.sequeros.dam.pmdp.aplicacion.categorias

import ies.sequeros.dam.pmdp.aplicacion.categorias.dtos.CategoriaDto
import ies.sequeros.dam.pmdp.modelo.ICategoriaRepositorio

class GetCategoriaUseCase(
    private val repository: ICategoriaRepositorio
) {
    suspend operator fun invoke(id: String): CategoriaDto {
        val categoria = repository.findById(id)
            ?: throw NoSuchElementException("Categoria no encontrada con id $id")

        return CategoriaDto(
            id = categoria.id,
            nombre = categoria.nombre,
            descripcion = categoria.descripcion,
            activo = categoria.activo
        )
    }
}