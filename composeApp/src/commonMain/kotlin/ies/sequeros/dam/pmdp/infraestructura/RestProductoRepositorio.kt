package ies.sequeros.dam.pmdp.infraestructura

import ies.sequeros.dam.pmdp.modelo.IProductoRepositorio
import ies.sequeros.dam.pmdp.modelo.Producto
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.*
import io.ktor.http.*
import kotlinx.serialization.Serializable

class RestProductoRepositorio(private val url: String, private val _client: HttpClient) : IProductoRepositorio {

    @Serializable
    private data class AddProductoRequest(
        val nombre: String,
        val descripcion: String,
        val precio: String, // String para evitar errores con BigDecimal
        val activo: Boolean,
        val categoriaId: String
    )

    @Serializable
    private data class UpdateProductoRequest(
        val id: String,
        val nombre: String,
        val descripcion: String,
        val precio: String, // String
        val activo: Boolean,
        val categoriaId: String
    )

    override suspend fun all(): List<Producto> {
        return _client.get(url).body()
    }

    override suspend fun create(item: Producto) {
        // Convertimos el producto al formato que el servidor acepta
        val request = AddProductoRequest(
            nombre = item.nombre,
            descripcion = item.descripcion,
            precio = item.precio.toString(), // Importante: toString()
            activo = item.activo,
            categoriaId = item.categoriaId
        )

        _client.post(url) {
            contentType(ContentType.Application.Json)
            setBody(request)
        }
    }

    override suspend fun update(item: Producto) {
        val request = UpdateProductoRequest(
            id = item.id,
            nombre = item.nombre,
            descripcion = item.descripcion,
            precio = item.precio.toString(),
            activo = item.activo,
            categoriaId = item.categoriaId
        )

        _client.put("$url/${item.id}") {
            contentType(ContentType.Application.Json)
            setBody(request)
        }
    }

    override suspend fun delete(item: Producto?) {
        if (item == null) return
        delete(item.id)
    }

    override suspend fun delete(id: String) {
        _client.delete("$url/$id")
    }

    override suspend fun findById(id: String): Producto? {
        return try {
            _client.get("$url/$id").body()
        } catch (e: Exception) {
            println("Error buscando producto $id: ${e.message}")
            null
        }
    }

    override suspend fun existsById(id: String): Boolean {
        return try {
            val response = _client.head("$url/$id")
            response.status == HttpStatusCode.OK
        } catch (e: Exception) {
            false
        }
    }
}