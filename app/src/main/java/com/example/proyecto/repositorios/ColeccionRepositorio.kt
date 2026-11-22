package com.example.proyecto.repositorios

import com.example.proyecto.R
import com.example.proyecto.modelos.Carta
import com.example.proyecto.modelos.CartaColeccion

class ColeccionRepositorio {

    private val cartas = mutableListOf<CartaColeccion>()

    private var siguienteId = 1

    init {

        cartas.add(
            CartaColeccion(
                id = siguienteId++,
                carta = Carta(
                    nombre = "Lightning Bolt",
                    tipo = "Instantáneo",
                    edicion = "Alpha",
                    imagenUrl = R.drawable.lightnign_bolt,
                    descripcion = "Lightning Bolt deals 3 damage to any target."
                ),
                cantidad = 1
            )
        )

        cartas.add(
            CartaColeccion(
                id = siguienteId++,
                carta = Carta(
                    nombre = "Llanowar Elves",
                    tipo = "Criatura — Elfo Druida",
                    edicion = "Magic: The Gathering - Cimientos",
                    imagenUrl = R.drawable.llanowar_elves,
                    descripcion = "{T}: Add {G}."
                ),
                cantidad = 1
            )
        )

        cartas.add(
            CartaColeccion(
                id = siguienteId++,
                carta = Carta(
                    nombre = "Black Lotus",
                    tipo = "Artefacto",
                    edicion = "Alpha",
                    imagenUrl = R.drawable.blacklotos,
                    descripcion = "{T}, Sacrifice Black Lotus: Add three mana of any one color."
                ),
                cantidad = 1
            )
        )

        cartas.add(
            CartaColeccion(
                id = siguienteId++,
                carta = Carta(
                    nombre = "Counterspell",
                    tipo = "Instantáneo",
                    edicion = "Commander: Foundations",
                    imagenUrl = R.drawable.counter,
                    descripcion = "Counter target spell."
                ),
                cantidad = 5
            )
        )
    }
    fun obtenerCartas(): List<CartaColeccion> {
        return cartas.toList()
    }

    fun actualizarCarta(id: Int, carta: Carta, cantidad: Int) {

        val posicion = cartas.indexOfFirst { it.id == id }

        if (posicion != -1) {

            cartas[posicion] = CartaColeccion(
                id = id,
                carta = carta,
                cantidad = cantidad
            )
        }
    }
    fun eliminarCarta(id: Int) {

        cartas.removeAll { it.id == id }
    }

    // Si la carta ya está en la colección, actualiza su cantidad
    // de lo contrario, agrégala a la colección
    fun anadirCarta(carta: Carta, cantidad: Int) {
        val posicion = cartas.indexOfFirst {
            it.carta.nombre == carta.nombre &&
                    it.carta.edicion == carta.edicion &&
                    it.carta.imagenUrl == carta.imagenUrl
        }

        if (posicion != -1) {
            val cartaExistente = cartas[posicion]

            cartas[posicion] = cartaExistente.copy(
                cantidad = cartaExistente.cantidad + cantidad
            )
        } else {
            val nuevaCarta = CartaColeccion(
                id = siguienteId++,
                carta = carta,
                cantidad = cantidad
            )

            cartas.add(nuevaCarta)
        }
    }
}