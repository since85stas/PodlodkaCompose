package stas.batura.podlodkacompose.data.room

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "sessions_table")
data class SessionN (
    @PrimaryKey
    var id: String,
    var speaker: String = "",
    var date: String = "",
    var timeInterval: String = "",
    var description: String = "",
    var imageUrl: String = ""
)

