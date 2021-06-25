package stas.batura.podlodkacompose.data.room

import android.os.Parcel
import android.os.Parcelable
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey

// базовый класс для хранения сессий
@Entity(tableName = "sessions_table")
data class Session (
    @PrimaryKey
    var id: String = "0",
    var speaker: String = "",
    var date: String = "",
    var timeInterval: String = "",
    var description: String = "",
    var imageUrl: String = ""
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!
    ) {
    }

//    @Ignore constructor(): this("0","","","","","")

    companion object CREATOR : Parcelable.Creator<Session> {
        override fun createFromParcel(parcel: Parcel): Session {
            return Session(parcel)
        }

        override fun newArray(size: Int): Array<Session?> {
            return arrayOfNulls(size)
        }
    }

    override fun describeContents(): Int {
        return 0
    }

    override fun writeToParcel(dest: Parcel?, flags: Int) {
        dest!!.writeStringArray(arrayOf(id,speaker,date,timeInterval, description, imageUrl))
    }
}