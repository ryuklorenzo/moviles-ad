package ies.sequeros.dam.pmdp.modelo

interface ICategoriaRepositorio {
    suspend fun all(): List<Categoria>
    suspend fun update(item: Categoria)
    suspend fun create(item: Categoria)
    suspend fun delete(item: Categoria?)
    suspend fun delete(id: String)
    suspend fun findById(id: String): Categoria?
    suspend fun existsById(id: String): Boolean
}