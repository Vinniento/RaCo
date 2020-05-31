package com.example.raco.data.api

import com.example.raco.models.DefaultResponse
import retrofit2.http.Field
import retrofit2.http.POST

interface UserDao {
    //TODO implement getters and setters for userData

    @POST("updatePassword")
    suspend fun updatePassword(
        @Field("email") email: String,
        @Field("newPassword") newPassword: String
    ): DefaultResponse
}