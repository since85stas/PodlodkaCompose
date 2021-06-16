package stas.batura.testapp.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent


@Module
@InstallIn(ApplicationComponent::class)
class RetrofitModule {

//    @Provides
//    fun providesRetrofitService(): IRetrofit {
//        return RetrofitClient.netApi.servise
//    }

}