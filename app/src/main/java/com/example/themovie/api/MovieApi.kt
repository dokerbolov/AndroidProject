package com.example.themovie.api

import com.example.themovie.Fav.RequestSession
import com.example.themovie.Fav.SessionId
import com.example.themovie.authorization.LoginData
import com.example.themovie.authorization.RequestToken

import retrofit2.Call
import retrofit2.http.*

interface MovieApi {


//     @POST("authentication/token/validate_with_login")
//     fun getRequestBody(@Query("api_key") apiKey: String): Call<RequestBody>

    @POST("authentication/token/validate_with_login?api_key=2f0d69a585b1ec8a833e56046239144b")
    fun login(@Body loginData: LoginData): Call<RequestToken>

    @POST("authentication/session/new?api_key=2f0d69a585b1ec8a833e56046239144b")
    fun getSession(@Body sessionId: SessionId): Call<RequestSession>

    @GET("authentication/token/new")
    fun getRequestToken(@Query("api_key") apiKey: String): Call<RequestToken>

    }