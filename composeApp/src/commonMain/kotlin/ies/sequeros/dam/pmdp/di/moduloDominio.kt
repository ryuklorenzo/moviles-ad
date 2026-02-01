package ies.sequeros.dam.pmdp.di

import ies.sequeros.dam.pmdp.infraestructura.RestCategoriaRepositorio
import ies.sequeros.dam.pmdp.infraestructura.RestProductoRepositorio
import ies.sequeros.dam.pmdp.modelo.ICategoriaRepositorio
import ies.sequeros.dam.pmdp.modelo.IProductoRepositorio
import org.koin.dsl.module

val moduloDominio = module {
    // repositorios
    // aqui poner la ruta de los endpoints en un futuro
    //single<IDigimonRepositorio> { RestDigimonRepositorio("https://digimon-api.vercel.app/api/digimon",get ()) }
    single<ICategoriaRepositorio>{ RestCategoriaRepositorio("http://127.0.0.1:8080/categorias", get ())}
    single<IProductoRepositorio>{ RestProductoRepositorio("http://127.0.0.1:8080/productos", get()) }
}