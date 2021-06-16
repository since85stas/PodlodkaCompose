package stas.batura.podlodkacompose.data.room

import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey

@Entity(tableName = "favourites")
data class Favourite constructor(
    var sessionId: String = "0",
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0
) {
    @Ignore
    constructor(): this("0",0)
}

