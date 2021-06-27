package stas.batura.podlodkacompose.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import stas.batura.podlodkacompose.data.net.IRetrofit
import stas.batura.podlodkacompose.data.net.RetrofitClient

@Module
@InstallIn(ApplicationComponent::class)
class RetrofitModule {

    @Provides
    fun providesRetrofitService(): IRetrofit {
        return RetrofitClient.netApi.servise
    }

}