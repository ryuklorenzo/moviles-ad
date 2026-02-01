package ies.sequeros.dam.pmdp.vista.componentes

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Error
import androidx.compose.material.icons.filled.Pets
import androidx.compose.material3.Icon
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.unit.dp
import ies.sequeros.dam.pmdp.modelo.Digimon

@Composable
fun PanelListadoDigimon(
    items: List<Digimon>,
    selected: Digimon?,
    onSelect: (Digimon) -> Unit
) {
    Surface {
        if (items.isEmpty()) {
            // Mientras la petición REST carga, mostrar un aviso o carga
            Text("Cargando Digimons...", modifier = Modifier.padding(16.dp))
        } else {
            LazyColumn(modifier = Modifier.fillMaxSize()) {

                items(items, key = { it.name })  { item ->
                    ListItem(
                        headlineContent = {
                            Row() {
                                Icon(
                                    imageVector = Icons.Default.Pets,
                                    contentDescription = "",
                                    modifier = Modifier.size(24.dp),
                                    tint = MaterialTheme.colorScheme.primary
                                )
                                Text(item.name)
                            }
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable {


                                onSelect(item)
                            }
                            .padding(horizontal = 16.dp, vertical = 8.dp),
                        supportingContent = {
                            Text(
                                text = item.level,
                                maxLines = 1
                            )

                        },
                        tonalElevation = if (item.name == selected?.name) 4.dp else 0.dp
                    )
                }
            }
        }
    }
}