package com.example.raco.data.api.retrofit

import com.example.raco.models.DefaultResponse
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object AuthRepository {
    private val authDao: AuthDao
    const val _BASE_URL = "https://gist.github.com/Vinniento/"

    init {
        /*   val okHttpClient = OkHttpClient.Builder()
               .addInterceptor { chain ->
                   val original = chain.request()
                   val requestBuilder = original.newBuilder()
                       .addHeader("Authorization", "")
                       .method(original.method(), original.body())
                   val request = requestBuilder.build()
                   chain.proceed(request)
               }.build() */

        val retrofit = Retrofit.Builder()
            .baseUrl(_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            // .client(okHttpClient) ist aus dem "#3 Kotlin Retrofit Tutorial - SignUp using Retrofit POST" tut - notwendig?
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
}