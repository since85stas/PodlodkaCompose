package stas.batura.podlodkacompose.data.out

import stas.batura.podlodkacompose.data.room.Session
import stas.batura.podlodkacompose.data.room.SessionFav
import java.util.*
import kotlin.collections.HashMap

data class SessionDay(
    var day: String = "",
    var sessions: List<SessionFav> = emptyList()
)

fun getSessionDays(sessions: List<SessionFav>): Map<String, MutableList<SessionFav>> {
    val dayMap = TreeMap<String, MutableList<SessionFav>>()
    for (session in sessions) {
        if (dayMap.contains(session.date)) {
            dayMap.get(session.date)!!.add(session)
        } else {
            dayMap.put(session.date, mutableListOf())
            dayMap.get(session.date)!!.add(session)
        }
    }
    val days = mutableListOf<SessionDay>()
    dayMap.forEach { (t, u) ->
        val day = SessionDay(t,u)
        days.add(day)
    }
    return dayMap
}
