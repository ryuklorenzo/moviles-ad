package ies.sequeros.dam.pmdp.di

import ies.sequeros.dam.pmdp.aplicacion.categorias.AddCategoriaUseCase
import ies.sequeros.dam.pmdp.aplicacion.categorias.DeleteCategoriaUseCase
import ies.sequeros.dam.pmdp.aplicacion.categorias.GetAllCategoriaUseCase
import ies.sequeros.dam.pmdp.aplicacion.categorias.GetCategoriaUseCase
import ies.sequeros.dam.pmdp.aplicacion.categorias.UpdateCategoriaUseCase
import ies.sequeros.dam.pmdp.aplicacion.productos.AddProductoUseCase
import ies.sequeros.dam.pmdp.aplicacion.productos.DeleteProductoUseCase
import ies.sequeros.dam.pmdp.aplicacion.productos.GetAllProductosUseCase
import ies.sequeros.dam.pmdp.aplicacion.productos.GetProductoUseCase
import ies.sequeros.dam.pmdp.aplicacion.productos.GetProductoWithCategoriaUseCase
import ies.sequeros.dam.pmdp.aplicacion.productos.UpdateProductoUseCase
import org.koin.dsl.module

val moduloAplicacion = module {
    // casos de uso
    //factory { GetAllDigimonsUseCase(get()) }
    factory { GetCategoriaUseCase(get()) } //, get()) }
    factory { AddCategoriaUseCase(get()) }
    factory { UpdateCategoriaUseCase(get()) }
    factory { DeleteCategoriaUseCase(get()) }
    factory { GetAllCategoriaUseCase(get()) }

    factory { AddProductoUseCase(get()) }
    factory { DeleteProductoUseCase(get()) }
    factory { GetAllProductosUseCase(get()) }
    factory { GetProductoUseCase(get()) }
    factory { GetProductoWithCategoriaUseCase(get(), get()) }
    factory { UpdateProductoUseCase(get()) }
}