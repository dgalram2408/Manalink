package com.example.proyecto.navegacion


import android.net.Uri

object Rutas {

    const val LISTA = "lista"
    const val COLECCION = "coleccion"

    const val DETALLE = "detalle/{nombre}"
    const val DETALLE_COLECCION = "detalleColeccion/{id}"

    fun detalle(nombre: String): String {
        return "detalle/${Uri.encode(nombre)}"
    }
    fun detalleColeccion(id: Int): String {
        return "detalleColeccion/$id"
    }
}