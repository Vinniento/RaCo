package com.example.raco.data.api.retrofit

import com.example.raco.models.DefaultResponse
import com.example.raco.models.TestResponse
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object AuthRepository {
    private val authDao: AuthDao
    private const val _BASE_URL = "https://gist.github.com/Vinniento/"
    private const val _TEST_URL = "http://ip.jsontest.com"

    init {

        val retrofit = Retrofit.Builder()
            .baseUrl(_TEST_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        authDao = retrofit.create(AuthDao::class.java)
    }

    suspend fun register(
        email: String,
        firstname: String,
        lastname: String,
        password: String
    ): DefaultResponse {
        return authDao.createUser(email, firstname, lastname, password)
    }

    suspend fun login(email: String, password: String): DefaultResponse {
        return authDao.login(email, password)
    }

    suspend fun test(): DefaultResponse {
        return authDao.test()
    }

    suspend fun testIP(): TestResponse {
        return authDao.testIP()
    }
}