package ies.sequeros.dam.pmdp.aplicacion.categorias

import ies.sequeros.dam.pmdp.modelo.Categoria
import ies.sequeros.dam.pmdp.modelo.ICategoriaRepositorio

class GetAllCategoriaUseCase(private val categoriaRepositorio: ICategoriaRepositorio) {
    suspend fun invoke():List<Categoria>{
        return categoriaRepositorio.all()
    }
}