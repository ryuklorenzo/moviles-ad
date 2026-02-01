package ies.sequeros.dam.pmdp.vista.productos

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import ies.sequeros.dam.pmdp.DispatcherIO
import ies.sequeros.dam.pmdp.aplicacion.productos.AddProductoUseCase
import ies.sequeros.dam.pmdp.aplicacion.productos.DeleteProductoUseCase
import ies.sequeros.dam.pmdp.aplicacion.productos.GetAllProductosUseCase
import ies.sequeros.dam.pmdp.aplicacion.productos.UpdateProductoUseCase
import ies.sequeros.dam.pmdp.aplicacion.productos.commands.AddProductoCommand
import ies.sequeros.dam.pmdp.aplicacion.productos.commands.UpdateProductoCommand
import ies.sequeros.dam.pmdp.modelo.Producto
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlin.random.Random

class ProductosViewModel(
    private val getAllUseCase: GetAllProductosUseCase,
    private val addUseCase: AddProductoUseCase,
    private val updateUseCase: UpdateProductoUseCase,
    private val deleteUseCase: DeleteProductoUseCase
) : ViewModel() {

    private val _state = MutableStateFlow<List<Producto>>(emptyList())
    val state = _state.asStateFlow()

    private val _isLoading = MutableStateFlow(false)
    val isLoading = _isLoading.asStateFlow()

    fun loadProductos(categoriaId: String) {
        viewModelScope.launch {
            _isLoading.value = true
            try {
                withContext(DispatcherIO) {
                    // Obtenemos todos y filtramos.
                    // Lo ideal sería un GetProductosByCategoriaUseCase, pero usamos lo que tenemos.
                    val allProductos = getAllUseCase.invoke()
                    _state.value = allProductos.filter { it.categoriaId == categoriaId }
                }
            } catch (e: Exception) {
                e.printStackTrace()
            } finally {
                _isLoading.value = false
            }
        }
    }

    fun addProducto(categoriaId: String, nombre: String, descripcion: String, precio: Float, activo: Boolean) {
        viewModelScope.launch {
            try {
                withContext(DispatcherIO) {
                    val id = Random.nextLong().toString()
                    addUseCase(AddProductoCommand(id, nombre, descripcion, categoriaId, precio, activo))
                    loadProductos(categoriaId)
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    fun updateProducto(id: String, categoriaId: String, nombre: String, descripcion: String, precio: Float, activo: Boolean) {
        viewModelScope.launch {
            try {
                withContext(DispatcherIO) {
                    updateUseCase(UpdateProductoCommand(id, nombre, descripcion, categoriaId, precio, activo))
                    loadProductos(categoriaId)
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    fun deleteProducto(id: String, categoriaId: String) {
        viewModelScope.launch {
            try {
                withContext(DispatcherIO) {
                    deleteUseCase(id)
                    loadProductos(categoriaId)
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}