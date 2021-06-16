package stas.batura.podlodkacompose.data.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface SessionsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSession(session: Session)

    @Query("SELECT * from sessions_table ORDER BY id")
    fun getAllSessions() : Flow<List<Session>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllSessions(sessions: List<Session>)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertToFav(fav: Favourite)
}