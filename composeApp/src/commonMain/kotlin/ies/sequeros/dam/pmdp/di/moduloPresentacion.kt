package ies.sequeros.dam.pmdp.di

import ies.sequeros.dam.pmdp.vista.categorias.CategoriasViewModel
import ies.sequeros.dam.pmdp.vista.productos.ProductosViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val moduloPresentacion = module {
    viewModel { CategoriasViewModel(get(), get(), get(), get()) }
    viewModel { ProductosViewModel(get(), get(), get(), get()) }
}