package stas.batura.podlodkacompose.data

import stas.batura.podlodkacompose.data.room.SessionsDao
import javax.inject.Inject

class Repository: IRepository {

    @Inject
    lateinit var sessDao: SessionsDao


}