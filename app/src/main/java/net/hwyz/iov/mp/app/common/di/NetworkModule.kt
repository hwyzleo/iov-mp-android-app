package net.hwyz.iov.mp.app.common.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import net.hwyz.iov.mp.app.data.http.ApiCall
import net.hwyz.iov.mp.app.data.http.HttpService
import okhttp3.OkHttpClient
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    @Singleton
    @Provides
    fun provideApiService(): HttpService = ApiCall.retrofit

    @Singleton
    @Provides
    fun provideOkHttp(): OkHttpClient = ApiCall.okHttp
}