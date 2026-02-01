package ies.sequeros.dam.pmdp.infraestructura

import ies.sequeros.dam.pmdp.modelo.Categoria
import ies.sequeros.dam.pmdp.modelo.ICategoriaRepositorio
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.*
import io.ktor.http.*


class RestCategoriaRepositorio(private val url:String,private val _client: HttpClient) : ICategoriaRepositorio {
    override suspend fun all(): List<Categoria> {
        val request = this._client.get(url)
        val items: List<Categoria> = request.body()
        return items
    }

    override suspend fun update(item: Categoria) {
        _client.post(url) {
            contentType(ContentType.Application.Json)
            setBody(item)
        }
    }

    override suspend fun create(item: Categoria) {
        _client.put("$url/${item.id}") {
            contentType(ContentType.Application.Json)
            setBody(item)
        }
    }

    override suspend fun delete(item: Categoria?) {
        if (item == null) return
        _client.delete("$url/${item.id}")
    }

    override suspend fun delete(id: String) {
        _client.delete("$url/$id")
    }

    override suspend fun findById(id: String): Categoria? {
        return try {
            _client.get("$url/$id").body()
        } catch (e: Exception) {
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