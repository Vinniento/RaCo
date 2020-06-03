package com.example.raco.data.api

class Constants {
    companion object http {
        val BASE_URL: String = "http://192.168.1.119/raco/public/api/"
        val HOME: String = "$BASE_URL/api"
        val LOGIN_URL: String = "$HOME/login"
        val REGISTER_URL: String = "$HOME/register"


    }
}