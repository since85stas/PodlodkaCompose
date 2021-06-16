package stas.batura.podlodkacompose.data.out

import stas.batura.podlodkacompose.data.room.Session

data class SessionDay(
    var day: String = "",
    var sessions: List<Session> = emptyList()
)

fun getSessionDays(sessions: List<Session>): List<SessionDay> {
    val dayMap = HashMap<String, MutableList<Session>>()
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
    return days
}
