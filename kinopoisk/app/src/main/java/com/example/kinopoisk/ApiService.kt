package com.example.kinopoisk

import retrofit2.Call
import retrofit2.http.GET

interface ApiService {

    @GET("movies")
    fun getMovieList(): Call<List<Movie>>
}