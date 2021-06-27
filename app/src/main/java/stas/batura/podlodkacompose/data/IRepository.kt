package stas.batura.podlodkacompose.data

import kotlinx.coroutines.flow.Flow
import stas.batura.podlodkacompose.data.room.Favourite
import stas.batura.podlodkacompose.data.room.Session

interface IRepository {

    suspend fun addInitsessions()

    suspend fun addInitsessionsNet()

    fun getSessions(): Flow<List<Session>>

    fun getFavSessions(): Flow<List<Session>>

    suspend fun insertFav(session: Session): FavResult

    fun deleteFav(session: Session)

    fun getFavourites(): Flow<List<Favourite>>

    fun getError(): Flow<String>

}