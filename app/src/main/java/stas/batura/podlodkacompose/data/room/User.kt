package stas.batura.podlodkacompose.data.room

import androidx.room.Entity
import androidx.room.PrimaryKey
import stas.batura.podlodkacompose.data.net.UserResponse

@Entity(tableName = "users_table")
data class User (

    @PrimaryKey()
    var userId: Int = 0,

    var email: String? = "",

    var firstName: String? = "",

    var lastName: String? = "",

    var avatUrl: String? = ""
){

    object FromUserResponse {

        fun build(respons: UserResponse): User {

            return User(
                userId = respons.userId,
                email = respons.email,
                firstName = respons.firstName,
                lastName = respons.lastName,
                avatUrl = respons.avatUrl
            )
        }

    }
}





