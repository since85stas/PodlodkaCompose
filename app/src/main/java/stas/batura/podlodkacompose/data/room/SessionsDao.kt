package stas.batura.podlodkacompose.data.room

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface SessionsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSession(session: Session)

    @Query("SELECT * from sessions_table ORDER BY id")
    fun getAllSessions() : Flow<List<Session>>

    @Query("SELECT * FROM sessions_table WHERE id IN (SELECT DISTINCT(sessionId) FROM favourites)")
    fun getFavouriteSessions(): Flow<List<Session>>

    @Query("SELECT * FROM favourites ORDER BY sessionId")
    fun getFavourites(): Flow<List<Favourite>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllSessions(sessions: List<Session>)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertToFav(fav: Favourite)

    @Delete
    suspend fun removeFromFav(fav: Favourite)
}