package stas.batura.podlodkacompose.data

sealed class FavResult()

object Ok: FavResult()
data class Error(val err: String): FavResult()

