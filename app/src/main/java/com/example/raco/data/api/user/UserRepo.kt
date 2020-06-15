package com.example.raco.data.api.user

import com.example.raco.models.DefaultResponse
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object UserRepo {
    private val USER_DAO: UserDao
    private const val _BASE_URL: String = "http://192.168.0.123/raco/public/api/"

    init {
        val retrofit = Retrofit.Builder()
            .baseUrl(_BASE_URL)
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

    suspend fun addplayer(
        playerFirstName: String, playerLastName: String
    ): DefaultResponse {
        return USER_DAO.addplayer(playerFirstName, playerLastName, "hallo@z.at", "12341234")
    }
}