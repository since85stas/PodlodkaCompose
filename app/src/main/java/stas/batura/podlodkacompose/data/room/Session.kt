package stas.batura.podlodkacompose.data.room

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "sessions_table")
data class Session(

    @PrimaryKey
    val id: String,
    val speaker: String,
    val date: String,
    val timeInterval: String,
    val description: String,
    val imageUrl: String
)