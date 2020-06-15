package com.example.raco.data.api.user

import com.example.raco.models.DefaultResponse
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface UserDao {
    //TODO change to POST later on?
    @POST("register")
    suspend fun register(
        @Query("firstname") firstname: String,
        @Query("lastname") lastname: String,
        @Query("email") email: String,
        @Query("password") password: String
    ): DefaultResponse

    @GET("login")
    suspend fun login(
        @Query("email") email: String,
        @Query("password") password: String
    ): DefaultResponse

    @POST("addplayer")
    suspend fun addplayer(
        @Query("playerFirstName") playerFirstName: String,
        @Query("playerLastName") playerLastName: String,
        @Query("email") email: String,
        @Query("password") password: String
    ): DefaultResponse
}