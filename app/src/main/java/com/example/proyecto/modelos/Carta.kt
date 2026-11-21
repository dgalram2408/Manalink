package com.example.proyecto.modelos

data class Carta(
    val nombre: String,
    val tipo: String,
    val edicion: String,
    val imagenUrl: Int,
    val descripcion: String
    /*Contexto Descripciones de Cartas
      Notación	Significado
    {T}	Girar la carta (Tap)
    {UT} Enderezar la carata(Untap)
    {G}	Maná verde
    {R}	Maná rojo
    {U}	Maná azul
    {W}	Maná blanco
    {B}	Maná negro
    {2}	Dos manás genéricos
    {X}	Cantidad variable de maná
    */
)