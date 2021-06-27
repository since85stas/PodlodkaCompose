package stas.batura.podlodkacompose.ui.sessions

import stas.batura.podlodkacompose.data.room.Favourite
import stas.batura.podlodkacompose.data.room.Session
import stas.batura.podlodkacompose.data.room.SessionFav

suspend fun combineSessionsWithFavs(sess: List<Session>, favs: List<Favourite>): List<SessionFav> {
    val outList = mutableListOf<SessionFav>()
    val favBool = favs.map { it.sessionId }
    for (s in sess) {
        val out = SessionFav(s.id, s.speaker,s.date,s.timeInterval,s.description,s.imageUrl, s.id in favBool)
        outList.add(out)
    }
    return outList
}

suspend fun combineSessionsWithFilter(sess: List<Session>, filt: String): List<Session> {
    if (filt == "") return sess
    else return  sess.filter { sess -> sess.speaker.contains(filt) || sess.description.contains(filt) }
}

