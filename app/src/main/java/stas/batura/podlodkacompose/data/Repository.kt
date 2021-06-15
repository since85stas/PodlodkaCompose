package stas.batura.podlodkacompose.data

import android.util.Log
import kotlinx.coroutines.CoroutineScope
import stas.batura.podlodkacompose.data.room.SessionsDao
import stas.batura.podlodkacompose.di.ApplicationScope
import javax.inject.Inject

private val TAG = Repository::class.java.simpleName

class Repository: IRepository {

    @Inject
    lateinit var sessionsDao: SessionsDao

    @Inject
    @ApplicationScope
    lateinit var externalScope: CoroutineScope

    init {
        Log.d(TAG, ": rep init")
    }
}