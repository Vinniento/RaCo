package com.example.raco.data.api.retrofit

import com.example.raco.models.DefaultResponse
import retrofit2.http.Field
import retrofit2.http.GET

interface AuthDao {
    //TODO change to POST later on?
    @GET("/register")
    suspend fun createUser(
        @Field("email") email: String,
        @Field("firstname") firstname: String,
        @Field("lastname") lastname: String,
        @Field("password") password: String
    ): DefaultResponse

    @GET("login")
    suspend fun login(
        @Field("email") email: String,
        @Field("password") password: String
    ): DefaultResponse

    @GET("/1d2eeb629113837c6990f2e7cb1f3c47")
    suspend fun test(): DefaultResponse
}