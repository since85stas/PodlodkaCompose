package stas.batura.podlodkacompose.data.net

import retrofit2.http.*
import stas.batura.podlodkacompose.data.room.Session

interface IRetrofit {

    @GET("Sessions.json")
    suspend fun getUsers(): List<Session>

}