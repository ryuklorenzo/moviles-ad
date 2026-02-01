package ies.sequeros.dam.pmdp.infraestructura

import ies.sequeros.dam.pmdp.modelo.Producto
import ies.sequeros.dam.pmdp.modelo.IProductoRepositorio
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.*
import io.ktor.http.*


class RestProductoRepositorio(private val url:String,private val _client: HttpClient): IProductoRepositorio {
    override suspend fun all(): List<Producto> {
        return _client.get(url).body()
    }

    override suspend fun create(item: Producto) {
        // CORREGIDO: POST sin ID en URL
        _client.post(url) {
            contentType(ContentType.Application.Json)
            // Mapeamos para encajar con el servidor (sin ID, precio como double/float)
            setBody(mapOf(
                "nombre" to item.nombre,
                "descripcion" to item.descripcion,
                "precio" to item.precio,
                "activo" to item.activo,
                "categoriaId" to item.categoriaId
            ))
        }
    }

    override suspend fun delete(item: Producto?) {
        if (item == null) return
        _client.delete("$url/${item.id}")
    }

    override suspend fun delete(id: String) {
        _client.delete("$url/$id")
    }

    override suspend fun existsById(id: String): Boolean {
        return try {
            val response = _client.head("$url/$id")
            response.status == HttpStatusCode.OK
        } catch (e: Exception) {
            false
        }
    }

    override suspend fun findById(id: String): Producto? {
        return try {
            _client.get("$url/$id").body()
        } catch (e: Exception) {
            null
        }
    }

    override suspend fun update(item: Producto) {
        // CORREGIDO: PUT con ID en URL
        _client.put("$url/${item.id}") {
            contentType(ContentType.Application.Json)
            setBody(mapOf(
                "nombre" to item.nombre,
                "descripcion" to item.descripcion,
                "precio" to item.precio,
                "activo" to item.activo,
                "categoriaId" to item.categoriaId
            ))
        }
    }

}