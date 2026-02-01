package ies.sequeros.dam.pmdp.vista.productos

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import ies.sequeros.dam.pmdp.modelo.Producto
import ies.sequeros.dam.pmdp.vista.componentes.BarraBusqueda
import ies.sequeros.dam.pmdp.vista.componentes.BotonAnadir
import ies.sequeros.dam.pmdp.vista.componentes.BotonBorrar
import ies.sequeros.dam.pmdp.vista.componentes.BotonEditar
import org.koin.compose.viewmodel.koinViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProductosScreen(
    categoriaId: String,
    onBack: () -> Unit
) {
    val viewModel: ProductosViewModel = koinViewModel()
    val state by viewModel.state.collectAsState()
    val isLoading by viewModel.isLoading.collectAsState()

    var searchQuery by remember { mutableStateOf("") }
    var selectedProducto by remember { mutableStateOf<Producto?>(null) }
    var showForm by remember { mutableStateOf(false) }

    // Cargar productos al iniciar
    LaunchedEffect(categoriaId) {
        viewModel.loadProductos(categoriaId)
    }

    Scaffold(
        topBar = {
            TopAppBar( // Puedes usar TopAppBar directamente, que es el equivalente al antiguo Small por defecto
                title = { Text("Productos") },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Volver")
                    }
                }
            )
        },
        floatingActionButton = {
            // Asegúrate de que el nombre coincida con tu componente (BotonAnadir o BotonAnyadir)
            BotonAnadir {
                selectedProducto = null
                showForm = true
            }
        }
    ) { padding ->
        Column(modifier = Modifier.padding(padding).padding(16.dp)) {
            BarraBusqueda(query = searchQuery, onQueryChange = { searchQuery = it })

            if (isLoading) {
                CircularProgressIndicator()
            } else {
                val filteredList = state.filter {
                    it.nombre.contains(searchQuery, ignoreCase = true)
                }
                LazyColumn {
                    items(filteredList) { producto ->
                        ListItem(
                            headlineContent = { Text(producto.nombre) },
                            supportingContent = { Text("${producto.descripcion} - ${producto.precio}€") },
                            modifier = Modifier
                                .fillMaxWidth()
                                .clickable { selectedProducto = producto },
                            trailingContent = {
                                if (selectedProducto?.id == producto.id) {
                                    Row {
                                        BotonEditar { showForm = true }
                                        BotonBorrar {
                                            viewModel.deleteProducto(producto.id, categoriaId)
                                            selectedProducto = null
                                        }
                                    }
                                }
                            },
                            colors = ListItemDefaults.colors(
                                containerColor = if (selectedProducto?.id == producto.id)
                                    MaterialTheme.colorScheme.primaryContainer
                                else MaterialTheme.colorScheme.surface
                            )
                        )
                        HorizontalDivider()
                    }
                }
            }
        }
    }

    if (showForm) {
        AddProductoForm(
            producto = selectedProducto,
            onDismiss = { showForm = false },
            onSave = { nombre, descripcion, precio, activo ->
                if (selectedProducto == null) {
                    viewModel.addProducto(categoriaId, nombre, descripcion, precio.toDouble(), activo)
                } else {
                    viewModel.updateProducto(selectedProducto!!.id, categoriaId, nombre, descripcion, precio, activo)
                }
                showForm = false
                selectedProducto = null
            }
        )
    }
}