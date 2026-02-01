package ies.sequeros.dam.pmdp.vista.categorias

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import ies.sequeros.dam.pmdp.modelo.Categoria

@Composable
fun AddCategoriasForm(
    categoria: Categoria? = null,
    onDismiss: () -> Unit,
    onSave: (String, String, Boolean) -> Unit
) {
    var nombre by remember { mutableStateOf(categoria?.nombre ?: "") }
    var descripcion by remember { mutableStateOf(categoria?.descripcion ?: "") }
    var activo by remember { mutableStateOf(categoria?.activo ?: false) }

    Dialog(onDismissRequest = onDismiss) {
        Surface(
            shape = MaterialTheme.shapes.medium,
            tonalElevation = 8.dp,
            modifier = Modifier.padding(16.dp)
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text(
                    text = if (categoria == null) "Nueva Categoría" else "Editar Categoría",
                    style = MaterialTheme.typography.titleLarge
                )
                Spacer(modifier = Modifier.height(16.dp))

                OutlinedTextField(
                    value = nombre,
                    onValueChange = { nombre = it },
                    label = { Text("Nombre") },
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(8.dp))

                OutlinedTextField(
                    value = descripcion,
                    onValueChange = { descripcion = it },
                    label = { Text("Descripción") },
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(8.dp))

                Row(verticalAlignment = Alignment.CenterVertically) {
                    Checkbox(checked = activo, onCheckedChange = { activo = it })
                    Text("Activo")
                }

                Spacer(modifier = Modifier.height(16.dp))

                Row {
                    Button(onClick = onDismiss, modifier = Modifier.weight(1f)) {
                        Text("Cancelar")
                    }
                    Spacer(modifier = Modifier.padding(8.dp))
                    Button(
                        onClick = { onSave(nombre, descripcion, activo) },
                        modifier = Modifier.weight(1f)
                    ) {
                        Text("Guardar")
                    }
                }
            }
        }
    }
}