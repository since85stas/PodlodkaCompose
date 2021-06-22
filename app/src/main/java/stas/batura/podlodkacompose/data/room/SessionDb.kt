package stas.batura.podlodkacompose.data.room

import androidx.room.Entity

@Entity(tableName = "sessions_table")
data class SessionDb(
    val id: String,
    val speaker: String,
    val date: String,
    val timeInterval: String,
    val description: String,
    val imageUrl: String
)
