package com.example.raco.models

data class DefaultResponse(val error: String, val success: String)

data class LoginResponse(val success: String)

data class TestResponse(val response: String)