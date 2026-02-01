package ies.sequeros.dam.pmdp.modelo

interface IProductoRepositorio {
    suspend fun all(): List<Producto>
    suspend fun update(item: Producto)
    suspend fun create(item: Producto)
    suspend fun delete(item: Producto?)
    suspend fun delete(id: String)
    suspend fun findById(id: String): Producto?
    suspend fun existsById(id: String): Boolean
}