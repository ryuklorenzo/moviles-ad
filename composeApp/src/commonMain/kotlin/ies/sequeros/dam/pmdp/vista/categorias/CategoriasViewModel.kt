package ies.sequeros.dam.pmdp.vista.categorias

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import ies.sequeros.dam.pmdp.DispatcherIO
import ies.sequeros.dam.pmdp.aplicacion.categorias.AddCategoriaUseCase
import ies.sequeros.dam.pmdp.aplicacion.categorias.DeleteCategoriaUseCase
import ies.sequeros.dam.pmdp.aplicacion.categorias.GetAllCategoriaUseCase
import ies.sequeros.dam.pmdp.aplicacion.categorias.UpdateCategoriaUseCase
import ies.sequeros.dam.pmdp.aplicacion.categorias.commands.AddCategoriaCommand
import ies.sequeros.dam.pmdp.aplicacion.categorias.commands.UpdateCategoriaCommand
import ies.sequeros.dam.pmdp.modelo.Categoria
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlin.random.Random

class CategoriasViewModel(
    private val getAllUseCase: GetAllCategoriaUseCase,
    private val addUseCase: AddCategoriaUseCase,
    private val updateUseCase: UpdateCategoriaUseCase,
    private val deleteUseCase: DeleteCategoriaUseCase
) : ViewModel() {

    private val _state = MutableStateFlow<List<Categoria>>(emptyList())
    val state = _state.asStateFlow()

    private val _isLoading = MutableStateFlow(false)
    val isLoading = _isLoading.asStateFlow()

    init {
        loadCategorias()
    }

    fun loadCategorias() {
        viewModelScope.launch {
            _isLoading.value = true
            try {
                withContext(DispatcherIO) {
                    _state.value = getAllUseCase.invoke()
                }
            } catch (e: Exception) {
                e.printStackTrace()
            } finally {
                _isLoading.value = false
            }
        }
    }

    fun addCategoria(nombre: String, descripcion: String, activo: Boolean) {
        viewModelScope.launch {
            try {
                withContext(DispatcherIO) {
                    // Generar un ID simple para el cliente
                    val id = Random.nextLong().toString()
                    addUseCase(AddCategoriaCommand(id, nombre, descripcion, activo))
                    loadCategorias()
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    fun updateCategoria(id: String, nombre: String, descripcion: String, activo: Boolean) {
        viewModelScope.launch {
            try {
                withContext(DispatcherIO) {
                    updateUseCase(UpdateCategoriaCommand(id, nombre, descripcion, activo))
                    loadCategorias()
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    fun deleteCategoria(id: String) {
        viewModelScope.launch {
            try {
                withContext(DispatcherIO) {
                    deleteUseCase(id)
                    loadCategorias()
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}