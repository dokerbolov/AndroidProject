package com.example.kinopoisk.model

import com.google.gson.annotations.SerializedName

class Movie(
    @SerializedName("id") val id: Int,
    @SerializedName("vote_count") val voteCount: Int? = null,
    @SerializedName("poster_path") val poster_path: String? = null,
    @SerializedName("original_title") val originalTitle: String? = null,
    @SerializedName("overview") val overview: String? = null,
    @SerializedName("release_date") val releaseDate: String? = null
) {
    fun getPosterPath(): String {
        return "https://image.tmdb.org/t/p/w500" + poster_path
    }
}