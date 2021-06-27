package stas.batura.podlodkacompose.data

import android.util.Log
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import stas.batura.podlodkacompose.data.net.IRetrofit
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
    val netApi: IRetrofit,
    @ApplicationScope val externalScope: CoroutineScope
): IRepository {

    init {
        Log.d(TAG, ": rep init")
    }

    override fun getError(): Flow<String> {
        TODO("Not yet implemented")
    }

    // загрузка из файла
    override suspend fun addInitsessions() {
        sessionsDao.insertAllSessions(MockSessions)
    }

    // загрузка с сети
    override suspend fun addInitsessionsNet() {
        val fromNet = netApi.getUsers()
        sessionsDao.insertAllSessions(fromNet)
    }

    // список сессий
    override fun getSessions(): Flow<List<Session>> {
        return sessionsDao.getAllSessions()
    }

    // список избрвнных сессий
    override fun getFavSessions(): Flow<List<Session>> {
        return sessionsDao.getFavouriteSessions()
    }

    // добавление избранного
    override suspend fun insertFav(session: Session): FavResult {
            val inTable = sessionsDao.getNumberOfFavs()
            if (inTable < MAX_FAVOURITES) {
                sessionsDao.insertToFav(Favourite(session.id))
                return Ok
            } else {
                return Error("Не удалось добавить сессию в избранное")
            }
    }

    // удаление избранного
    override fun deleteFav(session: Session) {
        externalScope.launch {
            sessionsDao.removeFromFav(Favourite(session.id))
        }
    }

    // список избранного
    override fun getFavourites(): Flow<List<Favourite>> {
        return sessionsDao.getFavourites()
    }

}