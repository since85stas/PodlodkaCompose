package stas.batura.podlodkacompose.data.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import stas.batura.podlodkacompose.data.rawdata.Session

@Dao
interface SessionsDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertSession(session: Session): Long

    @Query("SELECT * from sessions_table ORDER BY id")
    fun getAllSessions() : Flow<List<Session>>

}