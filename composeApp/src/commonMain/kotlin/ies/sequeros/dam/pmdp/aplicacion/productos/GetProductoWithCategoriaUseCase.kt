package ies.sequeros.dam.pmdp.aplicacion.productos

import ies.sequeros.dam.pmdp.aplicacion.productos.dtos.ProductoWithCategoriasDto
import ies.sequeros.dam.pmdp.modelo.ICategoriaRepositorio
import ies.sequeros.dam.pmdp.modelo.IProductoRepositorio

class GetProductoWithCategoriaUseCase(
    private val productoRepo: IProductoRepositorio,
    private val categoriaRepo: ICategoriaRepositorio
) {
    suspend operator fun invoke(id: String): ProductoWithCategoriasDto {
        val producto = productoRepo.findById(id)
            ?: throw NoSuchElementException("Producto no encontrado")

        val categorias = categoriaRepo.all().filter { it.activo == true }

        return ProductoWithCategoriasDto(
            id = producto.id,
            nombre = producto.nombre,
            descripcion = producto.descripcion,
            categoriaId = producto.categoriaId,
            precio = producto.precio,
            activo = producto.activo,
            categorias = categorias.map {
                ies.sequeros.dam.pmdp.aplicacion.productos.dtos.CategoriaProductoDto(
                    id = it.id,
                    nombre = it.nombre,
                    descripcion = it.descripcion,
                    activo = it.activo
                )
            }
        )
    }
}