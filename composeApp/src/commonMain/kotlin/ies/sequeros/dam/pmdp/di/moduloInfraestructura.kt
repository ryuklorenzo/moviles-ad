package ies.sequeros.dam.pmdp.di

import ies.sequeros.dam.pmdp.infraestructura.RestCategoriaRepositorio
import ies.sequeros.dam.pmdp.infraestructura.RestProductoRepositorio
import ies.sequeros.dam.pmdp.modelo.ICategoriaRepositorio
import ies.sequeros.dam.pmdp.modelo.IProductoRepositorio
import io.ktor.client.HttpClient
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.http.ContentType
import io.ktor.http.contentType
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import org.koin.dsl.module

private const val SERVER_PORT = 8080

val moduloInfraestructura = module {

    single {
        HttpClient {
            install(ContentNegotiation) {
                json(Json {
                    prettyPrint = true
                    isLenient = true
                    ignoreUnknownKeys = true
                })
            }

            defaultRequest {
                url("http://localhost:$SERVER_PORT")
                contentType(ContentType.Application.Json)
            }
        }
    }

    single<ICategoriaRepositorio> { RestCategoriaRepositorio(get(), get()) }
    single<IProductoRepositorio> { RestProductoRepositorio(get(), get()) }
}