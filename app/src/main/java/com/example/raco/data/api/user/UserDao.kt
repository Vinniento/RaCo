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

    //TODO password gleich einfügen oder leer lassen bis player sich anmeldet?
    @POST("addplayer")
    suspend fun addplayer(
        @Query("firstname") firstname: String,
        @Query("lastname") lastname: String,
        @Query("email") email: String
    ): DefaultResponse
}