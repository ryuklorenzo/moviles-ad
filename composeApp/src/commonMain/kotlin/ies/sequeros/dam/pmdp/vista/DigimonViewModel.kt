package ies.sequeros.dam.pmdp.vista

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import ies.sequeros.dam.pmdp.DispatcherIO

import ies.sequeros.dam.pmdp.modelo.Digimon
import kotlinx.coroutines.Dispatchers

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class DigimonViewModel(getAllDigimonUseCase: GetAllDigimonsUseCase) : ViewModel() {
    private val _items = MutableStateFlow<List<Digimon>>(emptyList())

    val items = _items.asStateFlow()

    init {
        viewModelScope.launch {
            withContext(DispatcherIO) {
                _items.value = getAllDigimonUseCase.invoke()
            }
        }

    }
}