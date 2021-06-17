package stas.batura.podlodkacompose.data.out

import stas.batura.podlodkacompose.data.room.Session
import java.util.*
import kotlin.collections.HashMap

data class SessionDay(
    var day: String = "",
    var sessions: List<Session> = emptyList()
)

fun getSessionDays(sessions: List<Session>): Map<String, MutableList<Session>> {
    val dayMap = TreeMap<String, MutableList<Session>>()
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
