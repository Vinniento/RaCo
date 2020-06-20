package com.example.raco.data.api.user

import com.example.raco.models.DefaultResponse
import com.example.raco.models.PlayerResponse
import com.example.raco.models.TrainingResponse
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object UserRepo {
    private val USER_DAO: UserDao
    private const val _BASE_URL: String = "http://192.168.1.119/raco/public/api/"

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

    suspend fun addPlayer(
        playerFirstName: String, playerLastName: String, playerEmail: String
    ): DefaultResponse {
        return USER_DAO.addplayer(
            playerFirstName, playerLastName, playerEmail
        )
    }

    suspend fun getAllPlayers(): List<PlayerResponse> {
        return USER_DAO.getAllPlayers()
    }

    suspend fun addTraining(
        date: String
    ): DefaultResponse {
        return USER_DAO.addTraining(
            date
        )
    }

    suspend fun getAllTrainings(): List<TrainingResponse> {
        return USER_DAO.getAllTrainings()
    }

}