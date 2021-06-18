package stas.batura.podlodkacompose.data

import android.util.Log
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
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

@Singleton
class Repository @Inject constructor(
    val sessionsDao: SessionsDao,
    @ApplicationScope val externalScope: CoroutineScope
): IRepository {

    init {
        Log.d(TAG, ": rep init")
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

    override fun insertFav(session: Session) {
        externalScope.launch {
            sessionsDao.insertToFav(Favourite(session.id))
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

    //    override fun getDays(): Flow<List<SessionDay>> {
////        return sessionsDao.getAllSessions().map { s -> getSessionDays(s) }
//        return null
//    }
}