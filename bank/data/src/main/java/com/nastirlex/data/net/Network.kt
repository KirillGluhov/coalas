package com.nastirlex.data.net

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import com.nastirlex.data.net.core.CoreApi
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import java.util.concurrent.TimeUnit

object Network {
    val appJson: Json = Json {
        ignoreUnknownKeys = true
        coerceInputValues = true
    }

    fun getCoreRetrofit(client: OkHttpClient, json: Json): Retrofit = Retrofit.Builder()
        .client(client)
        .baseUrl("http://10.16.18.140/")
        .addConverterFactory(
            json.asConverterFactory(
                "application/json".toMediaType()
            )
        )
        .build()

    fun getHttpClient(): OkHttpClient {
        val httpClientBuilder = OkHttpClient.Builder().apply {
            connectTimeout(5, TimeUnit.MINUTES)
            readTimeout(5, TimeUnit.MINUTES)
            writeTimeout(5, TimeUnit.MINUTES)
            val logLevel = HttpLoggingInterceptor.Level.BODY
            addInterceptor(HttpLoggingInterceptor().setLevel(logLevel))
        }

        return httpClientBuilder.build()
    }

    fun getCoreApi(retrofit: Retrofit): CoreApi = retrofit.create(CoreApi::class.java)
}