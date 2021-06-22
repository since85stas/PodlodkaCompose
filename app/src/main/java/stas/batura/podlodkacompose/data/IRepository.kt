package stas.batura.podlodkacompose.data

import kotlinx.coroutines.flow.Flow
import stas.batura.podlodkacompose.data.out.SessionDay
import stas.batura.podlodkacompose.data.room.Favourite
import stas.batura.podlodkacompose.data.room.Session

interface IRepository {

    suspend fun addInitsessions()

    fun getSessions(): Flow<List<Session>>

//    fun getDays(): Flow<List<SessionDay>>
    fun getFavSessions(): Flow<List<Session>>

    suspend fun insertFav(session: Session): String

    fun deleteFav(session: Session)

    fun getFavourites(): Flow<List<Favourite>>

    fun getError(): Flow<String>

}