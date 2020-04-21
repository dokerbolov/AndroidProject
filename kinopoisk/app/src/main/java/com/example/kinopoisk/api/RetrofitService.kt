package com.example.kinopoisk.api

import android.util.Log
import okhttp3.OkHttpClient
import java.util.concurrent.TimeUnit
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import okhttp3.logging.HttpLoggingInterceptor

object RetrofitService{
    const val BASE_URL = "http://api.themoviedb.org/3/"

    private var retrofit: Retrofit? = null
    private var apiService: ApiService? = null

    fun getPostApi(): Retrofit? {
        if(retrofit == null) {
            retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }
        return retrofit
    }

    fun getApi(): ApiService? {
        apiService = getPostApi()?.create(ApiService::class.java)
        return apiService
    }

    private fun getOkHttp(): OkHttpClient{
        val okHttpClient = OkHttpClient.Builder()
            .connectTimeout(60, TimeUnit.SECONDS)
            .readTimeout(60, TimeUnit.SECONDS)
            .addInterceptor(getLogginInterceptor())
        return okHttpClient.build()
    }

    private fun getLogginInterceptor(): HttpLoggingInterceptor{
        return HttpLoggingInterceptor(logger = object : HttpLoggingInterceptor.Logger {
            override fun log(message: String) {
                Log.d("OkHttp", message)
            }
        }).apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
    }
}
