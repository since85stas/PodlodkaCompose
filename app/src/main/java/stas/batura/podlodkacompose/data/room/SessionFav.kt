package stas.batura.podlodkacompose.data.room

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

suspend fun combineSessionsWithFavs(sess: List<Session>, favs: List<Favourite>): List<SessionFav> {
    val outList = mutableListOf<SessionFav>()
    val favBool = favs.map { it.sessionId }
    for (s in sess) {
        val out = SessionFav(s.id, s.speaker,s.date,s.timeInterval,s.description,s.imageUrl, s.id in favBool)
        outList.add(out)
    }
    return outList
}
