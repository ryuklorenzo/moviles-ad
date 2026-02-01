package ies.sequeros.dam.pmdp.infraestructura

import ies.sequeros.dam.pmdp.aplicacion.categorias.commands.AddCategoriaCommand
import ies.sequeros.dam.pmdp.aplicacion.categorias.commands.UpdateCategoriaCommand
import ies.sequeros.dam.pmdp.modelo.Categoria
import ies.sequeros.dam.pmdp.modelo.ICategoriaRepositorio
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.*
import io.ktor.http.*

class RestCategoriaRepositorio(private val url: String, private val _client: HttpClient) : ICategoriaRepositorio {

    override suspend fun all(): List<Categoria> {
        return _client.get(url).body()
    }

    override suspend fun create(item: Categoria) {
        val command = AddCategoriaCommand(
            nombre = item.nombre,
            descripcion = item.descripcion,
            activo = item.activo
        )

        _client.post(url) {
            contentType(ContentType.Application.Json)
            setBody(command)
        }
    }

    override suspend fun update(item: Categoria) {
        val command = UpdateCategoriaCommand(
            id = item.id,
            nombre = item.nombre,
            descripcion = item.descripcion,
            activo = item.activo
        )

        _client.put("$url/${item.id}") {
            contentType(ContentType.Application.Json)
            setBody(command)
        }
    }

    override suspend fun delete(item: Categoria?) {
        if (item == null) return
        delete(item.id)
    }

    override suspend fun delete(id: String) {
        _client.delete("$url/$id")
    }

    override suspend fun findById(id: String): Categoria? {
        return try {
            _client.get("$url/$id").body()
        } catch (e: Exception) {
            println("Error buscando categoría $id: ${e.message}")
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