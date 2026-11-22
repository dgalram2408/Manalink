package com.example.proyecto.navegacion

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.proyecto.PantallaColeccion
import com.example.proyecto.PantallaDetalle
import com.example.proyecto.PantallaLista
import com.example.proyecto.modelos.Carta
import com.example.proyecto.repositorios.ColeccionRepositorio

@Composable
fun AppNavegacion(
    cartas: List<Carta>,
    coleccionRepositorio: ColeccionRepositorio
) {

    val navController = rememberNavController()

    var cartasColeccion by remember {
        mutableStateOf(coleccionRepositorio.obtenerCartas())
    }

    NavHost(
        navController = navController,
        startDestination = Rutas.LISTA
    ) {

        composable(Rutas.LISTA) {
            PantallaLista(
                cartas = cartas,
                onCartaSeleccionada = { carta ->
                    navController.navigate(
                        Rutas.detalle(carta.nombre)
                    )
                },
                onIrColeccion = {
                    navController.navigate(Rutas.COLECCION)
                }
            )
        }

        composable(
            route = Rutas.DETALLE,
            arguments = listOf(
                navArgument("nombre") {
                    type = NavType.StringType
                }
            )
        ) { backStackEntry ->

            val nombre = backStackEntry.arguments?.getString("nombre")

            val carta = cartas.firstOrNull {
                it.nombre == nombre
            }

            if (carta != null) {
                PantallaDetalle(
                    carta = carta,
                    onVolver = {
                        navController.popBackStack()
                    },
                    onAnadirColeccion = {
                        cartaAnadida, cantidad ->
                        coleccionRepositorio.anadirCarta(cartaAnadida, cantidad)

                        cartasColeccion = coleccionRepositorio.obtenerCartas()
                    }
                )
            } else {
                Text(text = "Carta no encontrada")
            }
        }
        composable(Rutas.COLECCION) {
            PantallaColeccion(
                cartas = cartasColeccion,
                onCartaSeleccionada = { entrada ->
                    navController.navigate(
                        Rutas.detalleColeccion(entrada.id)
                    )
                }
            )
        }
        composable(
            route = Rutas.DETALLE_COLECCION,
            arguments = listOf(
                navArgument("id") {
                    type = NavType.IntType
                }
            )
        ) { backStackEntry ->

            val id = backStackEntry.arguments?.getInt("id")

            val entrada = coleccionRepositorio.obtenerCartas()
                .firstOrNull { it.id == id }

            if (entrada != null) {
                PantallaDetalle(
                    carta = entrada.carta,
                    cantidad = entrada.cantidad,
                    onVolver = {
                        navController.popBackStack()
                    },
                    onEditarCantidad = { nuevaCantidad ->
                        coleccionRepositorio.actualizarCarta(
                            entrada.id,
                            entrada.carta,
                            nuevaCantidad
                        )

                        cartasColeccion = coleccionRepositorio.obtenerCartas()
                    },
                    onEliminarCarta = {
                        coleccionRepositorio.eliminarCarta(entrada.id)

                        cartasColeccion = coleccionRepositorio.obtenerCartas()
                        //Salimos del detalle de puesto que ya no existe
                        navController.popBackStack()
                    }
                )
            } else {
                Text(text = "Carta no encontrada en la colección")
            }
        }

    }
}