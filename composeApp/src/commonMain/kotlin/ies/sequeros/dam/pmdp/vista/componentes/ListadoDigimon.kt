package ies.sequeros.dam.pmdp.vista.componentes

import androidx.compose.material3.adaptive.ExperimentalMaterial3AdaptiveApi
import androidx.compose.material3.adaptive.currentWindowAdaptiveInfo
import androidx.compose.material3.adaptive.layout.ListDetailPaneScaffold
import androidx.compose.material3.adaptive.layout.ListDetailPaneScaffoldRole
import androidx.compose.material3.adaptive.layout.calculatePaneScaffoldDirective
import androidx.compose.material3.adaptive.navigation.rememberListDetailPaneScaffoldNavigator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.platform.LocalFocusManager
import androidx.window.core.layout.WindowSizeClass

import ies.sequeros.dam.pmdp.modelo.Digimon
import ies.sequeros.dam.pmdp.vista.DigimonViewModel
import kotlinx.coroutines.launch
import org.koin.compose.koinInject
import org.koin.compose.viewmodel.koinViewModel

@OptIn(ExperimentalMaterial3AdaptiveApi::class)
@Composable
fun ListadoDigimon() {
   //injeccion de dependencias
    val vm: DigimonViewModel= koinViewModel ()
    val navigator = rememberListDetailPaneScaffoldNavigator<Nothing>()
    // estado de selección
    var selectedItem by remember { mutableStateOf<Digimon?>(null) }
    val items=vm.items.collectAsState()
    //corrutina
    val scope = rememberCoroutineScope()

    // al seleccionar un elemento: cargar borrador desde el contenido
    fun onSelect(item: Digimon) {
        selectedItem = item
        scope.launch {
            navigator.navigateTo(ListDetailPaneScaffoldRole.Detail)
        }
    }
    //tamaño de la ventana, para mostrar o no el botón de back
    val windowInfo=currentWindowAdaptiveInfo()
    val directive = calculatePaneScaffoldDirective(windowInfo)
    val mostrarBotonAtras= directive.maxHorizontalPartitions == 1
    ListDetailPaneScaffold(
        directive = navigator.scaffoldDirective,
        value = navigator.scaffoldValue,
        listPane = {
            PanelListadoDigimon(
                items = items.value,
                selected = selectedItem,
                onSelect = ::onSelect
            )
        },
        detailPane = {
            DetalleDigion(
                item = selectedItem,
                onBack = {
                    scope.launch {
                        navigator.navigateBack()
                    }
                },
                mostrarBotonAtras
            )
        },
    )
}