package stas.batura.podlodkacompose.data

import android.util.Log
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import stas.batura.podlodkacompose.data.rawdata.MockSessions
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
}