package com.example.kinopoisk

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {

    @GET("movies")
    fun getMovieList(): Call<List<Movie>>

    @GET("posts/{id}")
    fun getMovieById(@Path("id") id: Int): Call<Movie>
}