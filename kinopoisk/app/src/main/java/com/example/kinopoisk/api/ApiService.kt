package com.example.kinopoisk.api

import com.example.kinopoisk.model.Movie
import com.example.kinopoisk.model.MovieResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET("movie/popular")
    fun getPopularMoviesList(@Query("api_key") apiKey: String, @Query("page") page: Int): Call<MovieResponse>

    @GET("movie/{movie_id}")
    fun getMovieDetail(@Path("movie_id") id: Int, @Query("api_key") apiKey: String): Call<Movie>
}