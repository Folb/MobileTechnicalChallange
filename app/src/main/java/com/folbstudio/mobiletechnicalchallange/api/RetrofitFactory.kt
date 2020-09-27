package com.folbstudio.mobiletechnicalchallange.api

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import okhttp3.logging.HttpLoggingInterceptor.*
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitFactory {

    fun getClient(baseUrl: String): Retrofit {
        val interceptor = HttpLoggingInterceptor()
            .apply {
                level = Level.BASIC
            }

        val client = OkHttpClient.Builder()
            .addInterceptor(interceptor)
            .build()

        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(client)
            .build()
    }

    fun getClientWithGsonConverter(baseUrl: String): Retrofit {
        val interceptor = HttpLoggingInterceptor()
            .apply {
                level = Level.BASIC
            }

        val client = OkHttpClient.Builder()
            .addInterceptor(interceptor)
            .build()

        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
    }
}