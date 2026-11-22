
package com.example.proyecto

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.safeDrawingPadding
import com.example.proyecto.modelos.Carta
import com.example.proyecto.navegacion.AppNavegacion
import com.example.proyecto.repositorios.ColeccionRepositorio
import com.example.proyecto.ui.theme.ProyectoTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        val cartas = listOf(
            Carta(
                nombre = "Lightning Bolt",
                tipo = "Instantáneo",
                edicion = "Alpha",
                imagenUrl = R.drawable.lightnign_bolt,
                descripcion = "Inflige 3 puntos de daño a un oponente."
            ),
            Carta(
                nombre = "Llanowar Elves",
                tipo = "Criatura — Elfo Druida",
                edicion = "Magic: The Gathering - Cimientos",
                imagenUrl = R.drawable.llanowar_elves,
                descripcion = "{T}: Añade {G}."
            ),
            Carta(
                nombre = "Black Lotus",
                tipo = "Artefacto",
                edicion = "Alpha",
                imagenUrl = R.drawable.blacklotos,
                descripcion = "{T}, Sacrifica Black Lotus: Agrega tres manas de un color."
            ),
            Carta(
                nombre = "Counterspell",
                tipo = "Instantáneo",
                edicion = "Commander: Foundations",
                imagenUrl = R.drawable.counter,
                descripcion = "Contrarresta el hechizo objetivo"

            )
        )
        val coleccionRepositorio = ColeccionRepositorio()
        setContent {
            ProyectoTheme {
                Box(
                    modifier = androidx.compose.ui.Modifier.safeDrawingPadding()
                ) {
                    AppNavegacion(cartas = cartas,
                        coleccionRepositorio = coleccionRepositorio)
                }
            }
        }
    }
}
