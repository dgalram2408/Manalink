
package com.example.proyecto

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.proyecto.modelos.Carta
import com.example.proyecto.ui.theme.Background

@Composable
fun PantallaDetalle(
    carta: Carta,
    onVolver: () -> Unit,
    //DetalleColeccion
    cantidad: Int? = null,
    onAnadirColeccion: ((Carta, Int) -> Unit)? = null,
    onEditarCantidad: ((Int) -> Unit)? = null,
    onEliminarCarta: (() -> Unit)? = null
) {

    var mostrarDialogo by remember { mutableStateOf(false) }
    var cantidadTexto by remember { mutableStateOf("1") }
    var mensajeError by remember { mutableStateOf("") }

    var mostrarDialogoEdicion by remember { mutableStateOf(false) }
    var cantidadEdicionTexto by remember { mutableStateOf("") }
    var mensajeErrorEdicion by remember { mutableStateOf("") }

    var cantidadActual by remember {
        mutableStateOf(cantidad ?: 0)
    }
    var mostrarDialogoEliminar by remember {
        mutableStateOf(false)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Background)
            .verticalScroll(rememberScrollState())
            .padding(24.dp),
        verticalArrangement = Arrangement.spacedBy(24.dp)
    ) {
        Text(
            text = "Detalle de carta",
            style = MaterialTheme.typography.headlineMedium,
            color = MaterialTheme.colorScheme.onBackground
        )

        Text(
            text = carta.nombre,
            style = MaterialTheme.typography.titleLarge,
            color = MaterialTheme.colorScheme.onBackground
        )

        Image(
            painter = painterResource(carta.imagenUrl),
            contentDescription = "Imagen de ${carta.nombre}",
            contentScale = ContentScale.Fit,
            modifier = Modifier
                .width(220.dp)
                .height(308.dp)
                .align(Alignment.CenterHorizontally)
        )

        Text(
            text = "Tipo: ${carta.tipo}",
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.onBackground
        )

        Text(
            text = "Edición: ${carta.edicion}",
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onBackground
        )

        if (cantidad != null) {
            Text(
                text = "Cantidad en mi colección: $cantidadActual",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onBackground
            )
            if (onEditarCantidad != null) {
                Button(
                    onClick = {
                        cantidadEdicionTexto = cantidad.toString()
                        mensajeErrorEdicion = ""
                        mostrarDialogoEdicion = true
                    }
                ) {
                    Text(text = "Editar cantidad")
                }
            }
            if (onEliminarCarta != null) {
                Button(
                    onClick = {
                        mostrarDialogoEliminar = true
                    },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.error,
                        contentColor = MaterialTheme.colorScheme.onError
                    )
                ) {
                    Text(text = "Eliminar de mi colección")
                }
            }
        }

        Text(
            text = "Descripción",
            style = MaterialTheme.typography.titleMedium,
            color = MaterialTheme.colorScheme.onBackground
        )

        Text(
            text = carta.descripcion,
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onBackground
        )

        if (onAnadirColeccion != null) {
            Button(
                onClick = {
                    cantidadTexto = "1"
                    mensajeError = ""
                    mostrarDialogo = true
                }
            ) {
                Text(text = "Añadir a mi colección")
            }
        }

        Button(
            onClick = onVolver,
            colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.primary,
                contentColor = MaterialTheme.colorScheme.onPrimary
            )
        ) {
            Text(text = "Volver")
        }
    }
    if (mostrarDialogoEdicion) {
        AlertDialog(
            onDismissRequest = {
                mostrarDialogoEdicion = false
            },
            title = {
                Text(text = "Editar cantidad")
            },
            text = {
                Column(
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Text(text = "Indica la nueva cantidad de cartas.")

                    OutlinedTextField(
                        value = cantidadEdicionTexto,
                        onValueChange = {
                            cantidadEdicionTexto = it
                            mensajeErrorEdicion = ""
                        },
                        label = {
                            Text(text = "Cantidad")
                        },
                        singleLine = true
                    )

                    if (mensajeErrorEdicion.isNotEmpty()) {
                        Text(
                            text = mensajeErrorEdicion,
                            color = MaterialTheme.colorScheme.error
                        )
                    }
                }
            },
            confirmButton = {
                TextButton(
                    onClick = {
                        val nuevaCantidad = cantidadEdicionTexto.toIntOrNull()

                        if (nuevaCantidad != null && nuevaCantidad > 0) {
                            onEditarCantidad?.invoke(nuevaCantidad)
                            mostrarDialogoEdicion = false
                            cantidadActual=nuevaCantidad
                        } else {
                            mensajeErrorEdicion = "Introduce una cantidad válida."
                        }
                    }
                ) {
                    Text(text = "Guardar")
                }
            },
            dismissButton = {
                TextButton(
                    onClick = {
                        mostrarDialogoEdicion = false
                    }
                ) {
                    Text(text = "Cancelar")
                }
            }
        )
    }
    if (mostrarDialogo) {
        AlertDialog(
            onDismissRequest = {
                mostrarDialogo = false
            },
            title = {
                Text(text = "Añadir carta")
            },
            text = {
                Column(
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Text(text = "¿Cuántas copias quieres añadir?")

                    OutlinedTextField(
                        value = cantidadTexto,
                        onValueChange = {
                            cantidadTexto = it
                            mensajeError = ""
                        },
                        label = {
                            Text(text = "Cantidad")
                        },
                        singleLine = true
                    )

                    if (mensajeError.isNotEmpty()) {
                        Text(
                            text = mensajeError,
                            color = MaterialTheme.colorScheme.error
                        )
                    }
                }
            },
            confirmButton = {
                TextButton(
                    onClick = {
                        val cantidadNueva = cantidadTexto.toIntOrNull()

                        if (cantidadNueva != null && cantidadNueva > 0) {
                            onAnadirColeccion?.invoke(carta, cantidadNueva)
                            mostrarDialogo = false
                        } else {
                            mensajeError = "Introduce una cantidad válida."
                        }
                    }
                ) {
                    Text(text = "Añadir")
                }
            },
            dismissButton = {
                TextButton(
                    onClick = {
                        mostrarDialogo = false
                    }
                ) {
                    Text(text = "Cancelar")
                }
            }
        )
    }
    if (mostrarDialogoEliminar) {
        AlertDialog(
            onDismissRequest = {
                mostrarDialogoEliminar = false
            },
            title = {
                Text(text = "Eliminar carta")
            },
            text = {
                Text(
                    text = "¿Estás seguro de que quieres eliminar esta carta de tu colección?"
                )
            },
            confirmButton = {
                TextButton(
                    onClick = {
                        onEliminarCarta?.invoke()
                        mostrarDialogoEliminar = false
                    }
                ) {
                    Text(text = "Eliminar")
                }
            },
            dismissButton = {
                TextButton(
                    onClick = {
                        mostrarDialogoEliminar = false
                    }
                ) {
                    Text(text = "Cancelar")
                }
            }
        )
    }
}
