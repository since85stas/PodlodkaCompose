package stas.batura.podlodkacompose.di

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import stas.batura.podlodkacompose.data.room.SessionDb
import stas.batura.podlodkacompose.data.room.SessionsDao
import stas.batura.podlodkacompose.data.room.SessionsDatabase
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
class RoomModule {

    @Provides
    fun providePressureDao(database: SessionsDatabase): SessionsDao {
        return database.sessionsDatabaseDao
    }

    @Provides
    @Singleton
    fun providesDatabase(@ApplicationContext appContext: Context): SessionsDatabase {
        return SessionsDatabase.getInstance(appContext)
    }

}