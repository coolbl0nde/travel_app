package com.example.travelmap.data.remote

import com.example.travelmap.domain.model.User

data class LoginResponse(
    val accessToken: String,
    val user: User
)

data class RegisterResponse(
    val accessToken: String,
    val user: User
)