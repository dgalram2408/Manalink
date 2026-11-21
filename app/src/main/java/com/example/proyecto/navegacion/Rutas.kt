package com.example.proyecto.navegacion


import android.net.Uri

object Rutas {

    const val LISTA = "lista"

    const val DETALLE = "detalle/{nombre}"

    fun detalle(nombre: String): String {
        return "detalle/${Uri.encode(nombre)}"
    }
}