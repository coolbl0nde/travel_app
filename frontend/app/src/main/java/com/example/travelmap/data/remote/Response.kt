package com.example.travelmap.data.remote

import com.example.travelmap.domain.model.User

data class AuthResponse(
    val accessToken: String,
    val user: User
)

data class UserResponse(
    val id: String,
    val name: String,
    val email: String
)