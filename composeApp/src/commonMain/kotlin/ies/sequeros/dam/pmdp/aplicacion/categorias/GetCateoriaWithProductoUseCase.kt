package ies.sequeros.dam.pmdp.aplicacion.categorias

import ies.sequeros.dam.pmdp.aplicacion.categorias.dtos.CategoriaWithProductosDto
import ies.sequeros.dam.pmdp.modelo.ICategoriaRepositorio
import ies.sequeros.dam.pmdp.modelo.IProductoRepositorio

class GetCategoriaWithProductosUseCase(
    private val categoriaRepo: ICategoriaRepositorio,
    private val productoRepo: IProductoRepositorio
) {
    suspend operator fun invoke(id: String): CategoriaWithProductosDto {
        val categoria = categoriaRepo.findById(id)
            ?: throw NoSuchElementException("Categoria no encontrada")

        val productos = productoRepo.all().filter { it.categoriaId == id }

        return CategoriaWithProductosDto(
            id = categoria.id,
            nombre = categoria.nombre,
            descripcion = categoria.descripcion,
            activo = categoria.activo,
            productos = productos.map {
                ies.sequeros.dam.pmdp.aplicacion.categorias.dtos.ProductoCategoriaDto(
                    id = it.id,
                    nombre = it.nombre,
                    descripcion = it.descripcion,
                    activo = it.activo
                )
            }
        )
    }
}