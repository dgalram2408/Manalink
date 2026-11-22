package com.example.proyecto

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.CardDefaults
import androidx.compose.ui.res.painterResource
import com.example.proyecto.modelos.Carta
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import com.example.proyecto.ui.theme.Background

fun buscarCartas(cartas: List<Carta>, texto: String): List<Carta> {

    val busqueda = texto.trim()

    val coincidenciasInicio = cartas.filter {
        it.nombre.startsWith(busqueda, ignoreCase = true)
    }

    return if (busqueda.isEmpty()) {
        cartas
    } else if (coincidenciasInicio.isNotEmpty()) {
        coincidenciasInicio
    } else {
        cartas.filter {
            it.nombre.contains(busqueda, ignoreCase = true)
        }
    }
}

@Composable
fun PantallaLista(
    cartas: List<Carta>,
    onCartaSeleccionada: (Carta) -> Unit,
    onIrColeccion: () -> Unit
) {Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Background)
            .padding(24.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {

        var textoBusqueda by rememberSaveable {
        mutableStateOf("")
        }
        val cartasFiltradas = buscarCartas(cartas, textoBusqueda)
        Text(
            text = "MANA LINK",
            style = MaterialTheme.typography.headlineLarge,
            color= MaterialTheme.colorScheme.onBackground
        )

        Text(
            text = "Catálogo de cartas",
            style = MaterialTheme.typography.titleMedium,
            color= MaterialTheme.colorScheme.onBackground
        )
        Button(
            onClick = onIrColeccion,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = "Mi colección")
        }
        OutlinedTextField(
            value = textoBusqueda,
            onValueChange = {
                textoBusqueda = it
            },
            label = {
                Text("Buscar carta")
            },
            singleLine = true,
            modifier = Modifier.fillMaxWidth(),
            colors = OutlinedTextFieldDefaults.colors(
                focusedTextColor = MaterialTheme.colorScheme.onSurface,
                unfocusedTextColor = MaterialTheme.colorScheme.onSurface,
                focusedBorderColor = MaterialTheme.colorScheme.primary,
                unfocusedBorderColor = MaterialTheme.colorScheme.secondary,
                focusedLabelColor = MaterialTheme.colorScheme.primary,
                unfocusedLabelColor = MaterialTheme.colorScheme.secondary,
                cursorColor = MaterialTheme.colorScheme.primary
            )
        )
        LazyVerticalGrid(
            columns = GridCells.Adaptive(160.dp),
            contentPadding = PaddingValues(4.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(cartasFiltradas) { carta ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable {
                            onCartaSeleccionada(carta)
                        },
                    shape = RoundedCornerShape(8.dp),
                    elevation = CardDefaults.cardElevation(
                        defaultElevation = 4.dp
                    ),
                    colors = CardDefaults.cardColors(
                        containerColor = MaterialTheme.colorScheme.surface
                    )
                ) {
                    Column(
                        modifier = Modifier.padding(12.dp),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        Image(
                            painter = painterResource(carta.imagenUrl),
                            contentDescription = "Imagen de ${carta.nombre}",
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(180.dp)
                        )

                        Text(
                            text = carta.nombre,
                            style = MaterialTheme.typography.titleSmall,
                            maxLines = 1,
                            softWrap = false,
                            overflow = TextOverflow.Ellipsis
                        )

                        Text(
                            text = carta.tipo,
                            style = MaterialTheme.typography.bodySmall
                        )
                    }
                }
            }
        }
    }
}
