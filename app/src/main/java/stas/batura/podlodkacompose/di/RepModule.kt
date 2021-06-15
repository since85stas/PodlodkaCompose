package stas.batura.podlodkacompose.di

import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.components.SingletonComponent
import stas.batura.podlodkacompose.data.IRepository
import stas.batura.podlodkacompose.data.Repository
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun bindRepos(repository: Repository): IRepository

}