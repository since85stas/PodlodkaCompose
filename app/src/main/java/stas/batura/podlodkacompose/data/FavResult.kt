package stas.batura.podlodkacompose.data

// класс для передачи ответа из БД, с возможной ошибкой
sealed class FavResult()

object Ok: FavResult()
data class Error(val err: String): FavResult()

