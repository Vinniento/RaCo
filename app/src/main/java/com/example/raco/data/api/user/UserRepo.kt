package com.example.raco.data.api.user

import com.example.raco.data.api.Constants
import com.example.raco.models.DefaultResponse
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object UserRepo {
    private val USER_DAO: UserDao

    init {
        val retrofit = Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        USER_DAO = retrofit.create(UserDao::class.java)
    }

    suspend fun register(
        firstname: String, lastname: String, email: String, password: String
    ): DefaultResponse {
        return USER_DAO.register(firstname, lastname, email, password)
    }

    suspend fun login(email: String, password: String): DefaultResponse {
        return USER_DAO.login(email, password)
    }

}