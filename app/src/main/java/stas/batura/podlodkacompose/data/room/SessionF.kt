package stas.batura.podlodkacompose.data.room

import androidx.room.PrimaryKey

data class SessionF(
    var id: String = "0",
    var speaker: String = "",
    var date: String = "",
    var timeInterval: String = "",
    var description: String = "",
    var imageUrl: String = "",
    var isFav: Boolean = false
)

suspend fun combineSessionsWithFavs(sess: List<Session>, favs: List<Favourite>): List<SessionF> {
    val outList = mutableListOf<SessionF>()
    val favBool = favs.map { it.sessionId }
    for (s in sess) {
        val out = SessionF(s.id, s.speaker,s.date,s.timeInterval,s.description,s.imageUrl, s.id in favBool)
        outList.add(out)
    }
    return outList
}
