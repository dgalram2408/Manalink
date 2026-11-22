package com.example.proyecto

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.proyecto.modelos.CartaColeccion
import com.example.proyecto.ui.theme.Background

@Composable
fun PantallaColeccion(
    cartas: List<CartaColeccion>,
    onCartaSeleccionada: (CartaColeccion) -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Background)
            .padding(24.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {

        Text(
            text = "Mi colección",
            style = MaterialTheme.typography.headlineLarge,
            color = MaterialTheme.colorScheme.onBackground
        )
        LazyVerticalGrid(
            columns = GridCells.Adaptive(160.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(cartas) { carta ->

                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable {
                            onCartaSeleccionada(carta)
                        },
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
                            painter = painterResource(carta.carta.imagenUrl),
                            contentDescription = "Imagen de ${carta.carta.nombre}",
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(180.dp)
                        )
                        Text(
                            text = carta.carta.nombre,
                            style = MaterialTheme.typography.titleSmall
                        )

                        Text(
                            text = carta.carta.edicion,
                            style = MaterialTheme.typography.bodySmall
                        )

                        Text(
                            text = "Cantidad: ${carta.cantidad}",
                            style = MaterialTheme.typography.bodyMedium
                        )
                    }
                }


            }
        }
    }
}


