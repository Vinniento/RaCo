package com.example.raco.models

data class DefaultResponse(val success: String)

data class PlayersList(val playersList: List<PlayerResponse>)
data class PlayerResponse(val firstname: String, val lastname: String)

