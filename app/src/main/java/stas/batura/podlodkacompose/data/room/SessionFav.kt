package stas.batura.podlodkacompose.data.room

// расширенный класс хранящий сесию и отображающий в избранном ли она
data class SessionFav(
    var id: String = "0",
    var speaker: String = "",
    var date: String = "",
    var timeInterval: String = "",
    var description: String = "",
    var imageUrl: String = "",
    var isFav: Boolean = false
) {
    fun toSession(): Session {
        return Session(id,speaker, date, timeInterval, description, imageUrl)
    }
}


