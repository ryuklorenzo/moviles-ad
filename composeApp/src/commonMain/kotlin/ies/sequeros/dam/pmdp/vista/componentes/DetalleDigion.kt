package ies.sequeros.dam.pmdp.vista.componentes


import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Error
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImagePainter
import coil3.compose.SubcomposeAsyncImage
import coil3.compose.SubcomposeAsyncImageContent
import digirest.composeapp.generated.resources.Res
import digirest.composeapp.generated.resources.img
import ies.sequeros.dam.pmdp.modelo.Digimon
import org.jetbrains.compose.resources.painterResource


@Composable
fun DetalleDigion(
    item: Digimon?,
    onBack: () -> Unit,
    mostrarBotonAtras: Boolean

) {
    if (item == null) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Text("Selecciona un elemento")
        }
        return
    }
    Surface(
        tonalElevation = 2.dp,
        modifier = Modifier.fillMaxSize()
    ) {
        Column(modifier = Modifier.padding(24.dp), horizontalAlignment = Alignment.CenterHorizontally,
           verticalArrangement =Arrangement.Center ) {
            Text(item.name, style = MaterialTheme.typography.headlineSmall)
            Spacer(Modifier.height(12.dp))
            Text(item.name)
            Spacer(Modifier.height(16.dp))
            SubcomposeAsyncImage(
                model = item.img,
                contentDescription = "",
            ){
                val state by painter.state.collectAsState()
                when (state){
                    is AsyncImagePainter.State.Success-> {
                        SubcomposeAsyncImageContent(
                            modifier = Modifier.fillMaxWidth(0.5f)
                        )
                    }
                    is AsyncImagePainter.State.Loading->{
                        CircularProgressIndicator(
                            color= MaterialTheme.colorScheme.primary
                        )

                    }
                    is AsyncImagePainter.State.Error->{
                        Image(
                            painter = painterResource(Res.drawable.img),
                            contentDescription = null
                        )
                    }
                    else -> {
                        //no deberia llegar aquí
                        Icon(
                            imageVector = Icons.Default.Error,
                            contentDescription = contentDescription,
                            modifier = Modifier.size(24.dp),
                            tint = MaterialTheme.colorScheme.primary
                        )
                    }
                }
            }
            if (mostrarBotonAtras) {
                Spacer(Modifier.height(16.dp))
                //Row(horizontalArrangement = Arrangement.Center, modifier = Modifier.fillMaxWidth()) {
                    Button(onClick = onBack) {
                        Text("Volver")
                    }
            }
        }
    }
}