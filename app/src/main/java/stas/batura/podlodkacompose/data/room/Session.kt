package stas.batura.podlodkacompose.data.room

import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey

@Entity(tableName = "sessions_table")
data class Session (
    @PrimaryKey
    var id: String = "0",
    var speaker: String = "",
    var date: String = "",
    var timeInterval: String = "",
    var description: String = "",
    var imageUrl: String = ""
) {
    @Ignore constructor(): this("0","","","","","")
}