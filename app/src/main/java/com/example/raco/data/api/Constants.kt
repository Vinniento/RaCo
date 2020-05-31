package com.example.raco.data.api

import java.net.URL

class Constants {
    companion object http {
        val BASE_URL: String = "http://192.168.1.119"
        val HOME: String = "$BASE_URL/api"
        val LOGIN_URL: String = "$HOME/login"
        val REGISTER_URL: String = "$HOME/register"
        val TEST_URL: URL =
            URL("https://gist.github.com/Vinniento/1d2eeb629113837c6990f2e7cb1f3c47")

    }
}