package stas.batura.podlodkacompose.data

import android.util.Log
import kotlinx.coroutines.CoroutineScope
import stas.batura.podlodkacompose.data.room.SessionsDao
import stas.batura.podlodkacompose.di.ApplicationScope
import javax.inject.Inject
import javax.inject.Singleton

private val TAG = Repository::class.java.simpleName

@Singleton
class Repository @Inject constructor(): IRepository {

    @Inject
    lateinit var sessionsDao: SessionsDao

    @Inject
    @ApplicationScope
    lateinit var externalScope: CoroutineScope

    init {
        Log.d(TAG, ": rep init")
    }

    override suspend fun addInitsessions() {
        TODO("Not yet implemented")
    }
}