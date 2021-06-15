package stas.batura.podlodkacompose.data.room

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favourites")
data class Favourite(
    var sessionId: String = "0"
) {
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}
