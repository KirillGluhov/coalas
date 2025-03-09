package com.nastirlex.bank.app.hilt

import com.nastirlex.bank.app.hilt.qualifiers.CoreRetrofit
import com.nastirlex.bank.app.hilt.qualifiers.CreditRetrofit
import com.nastirlex.bank.app.hilt.qualifiers.GateRetrofit
import com.nastirlex.data.net.Network
import com.nastirlex.data.net.core.CoreApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.json.Json
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
    @Singleton
    @Provides
    fun provideJson() = Network.appJson

    @Singleton
    @Provides
    fun provideCoreRetrofit(client: OkHttpClient, json: Json) = Network.getCoreRetrofit(client, json)

    @Singleton
    @Provides
    fun provideClient(): OkHttpClient = Network.getHttpClient()

    @Singleton
    @Provides
    fun provideCoreApi(retrofit: Retrofit): CoreApi = Network.getCoreApi(retrofit)
}