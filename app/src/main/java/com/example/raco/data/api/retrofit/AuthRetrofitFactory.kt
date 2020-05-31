package com.example.raco.data.api.retrofit

import retrofit2.Retrofit

//TODO nur zum testen, aus dem tut: https://android.jlelse.eu/kotlin-coroutines-and-retrofit-e0702d0b8e8f
object AuthRetrofitFactory {
    const val BASE_URL = "https://gist.github.com/Vinniento/"

    fun makeRetrofitService(): AuthDao {

        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .build().create(AuthDao::class.java)
    }
}