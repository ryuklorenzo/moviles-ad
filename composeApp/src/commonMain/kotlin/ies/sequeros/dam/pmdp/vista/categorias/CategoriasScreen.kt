package ies.sequeros.dam.pmdp.vista.categorias

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import ies.sequeros.dam.pmdp.modelo.Categoria
import ies.sequeros.dam.pmdp.vista.componentes.BarraBusqueda
import ies.sequeros.dam.pmdp.vista.componentes.BotonAnadir
import ies.sequeros.dam.pmdp.vista.componentes.BotonBorrar
import ies.sequeros.dam.pmdp.vista.componentes.BotonEditar
import ies.sequeros.dam.pmdp.vista.productos.ProductosScreen
import org.koin.compose.viewmodel.koinViewModel

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun CategoriasScreen() {
    val viewModel: CategoriasViewModel = koinViewModel()
    val state by viewModel.state.collectAsState()
    val isLoading by viewModel.isLoading.collectAsState()

    var searchQuery by remember { mutableStateOf("") }
    var selectedCategoria by remember { mutableStateOf<Categoria?>(null) }
    var showForm by remember { mutableStateOf(false) }

    // Estado para navegación simple a productos
    var categoriaParaNavegar by remember { mutableStateOf<Categoria?>(null) }

    if (categoriaParaNavegar != null) {
        // Navegamos a la pantalla de productos
        ProductosScreen(
            categoriaId = categoriaParaNavegar!!.id,
            onBack = { categoriaParaNavegar = null }
        )
    } else {
        Scaffold(
            floatingActionButton = {
                BotonAnadir {
                    selectedCategoria = null
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
                        items(filteredList) { categoria ->
                            ListItem(
                                headlineContent = { Text(categoria.nombre) },
                                supportingContent = { Text(categoria.descripcion) },
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .combinedClickable(
                                        onClick = { selectedCategoria = categoria },
                                        onDoubleClick = { categoriaParaNavegar = categoria }
                                    ),
                                trailingContent = {
                                    if (selectedCategoria?.id == categoria.id) {
                                        Row {
                                            BotonEditar { showForm = true }
                                            BotonBorrar {
                                                viewModel.deleteCategoria(categoria.id)
                                                selectedCategoria = null
                                            }
                                        }
                                    }
                                },
                                colors = ListItemDefaults.colors(
                                    containerColor = if (selectedCategoria?.id == categoria.id)
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
            AddCategoriasForm(
                categoria = selectedCategoria,
                onDismiss = { showForm = false },
                onSave = { nombre, descripcion, activo ->
                    if (selectedCategoria == null) {
                        viewModel.addCategoria(nombre, descripcion, activo)
                    } else {
                        viewModel.updateCategoria(selectedCategoria!!.id, nombre, descripcion, activo)
                    }
                    showForm = false
                    selectedCategoria = null
                }
            )
        }
    }
}