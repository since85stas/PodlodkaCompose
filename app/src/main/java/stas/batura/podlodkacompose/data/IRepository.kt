package stas.batura.podlodkacompose.data

import kotlinx.coroutines.flow.Flow
import stas.batura.podlodkacompose.data.room.Session

interface IRepository {

    suspend fun addInitsessions()

    fun getSessions(): Flow<List<Session>>

}