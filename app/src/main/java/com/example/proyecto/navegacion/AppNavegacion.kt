package com.example.proyecto.navegacion

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.proyecto.PantallaDetalle
import com.example.proyecto.PantallaLista
import com.example.proyecto.modelos.Carta

@Composable
fun AppNavegacion(cartas: List<Carta>) {

    val navController = rememberNavController()

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
                    }
                )
            } else {
                Text(text = "Carta no encontrada")
            }
        }
    }
}