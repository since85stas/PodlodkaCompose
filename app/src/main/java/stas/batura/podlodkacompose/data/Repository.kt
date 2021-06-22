package stas.batura.podlodkacompose.data

import android.util.Log
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import stas.batura.podlodkacompose.data.out.SessionDay
import stas.batura.podlodkacompose.data.out.getSessionDays
import stas.batura.podlodkacompose.data.rawdata.MockSessions
import stas.batura.podlodkacompose.data.room.Favourite
import stas.batura.podlodkacompose.data.room.Session
import stas.batura.podlodkacompose.data.room.SessionsDao
import stas.batura.podlodkacompose.di.ApplicationScope
import javax.inject.Inject
import javax.inject.Singleton

private val TAG = Repository::class.java.simpleName

private val MAX_FAVOURITES = 3

@Singleton
class Repository @Inject constructor(
    val sessionsDao: SessionsDao,
    @ApplicationScope val externalScope: CoroutineScope
): IRepository {

    init {
        Log.d(TAG, ": rep init")
    }

    override fun getError(): Flow<String> {
        TODO("Not yet implemented")
    }

    override suspend fun addInitsessions() {
        Log.d(TAG, "addInitsessions: ")
        sessionsDao.insertAllSessions(MockSessions)
    }

    override fun getSessions(): Flow<List<Session>> {
        return sessionsDao.getAllSessions()
    }

    override fun getFavSessions(): Flow<List<Session>> {
        return sessionsDao.getFavouriteSessions()
    }

    override suspend fun insertFav(session: Session): FavResult {
            val inTable = sessionsDao.getNumberOfFavs()
            if (inTable < MAX_FAVOURITES) {
                sessionsDao.insertToFav(Favourite(session.id))
                return Ok
            } else {
                return Error("Не удалось добавить сессию в избранное")
            }
    }

    override fun deleteFav(session: Session) {
        externalScope.launch {
            sessionsDao.removeFromFav(Favourite(session.id))
        }
    }

    override fun getFavourites(): Flow<List<Favourite>> {
        return sessionsDao.getFavourites()
    }

//    fun sendError(descr: String): Flow<String> {
//        error.
//    }

    //    override fun getDays(): Flow<List<SessionDay>> {
////        return sessionsDao.getAllSessions().map { s -> getSessionDays(s) }
//        return null
//    }
}