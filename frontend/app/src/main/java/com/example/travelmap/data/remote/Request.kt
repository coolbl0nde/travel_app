package com.example.travelmap.data.remote

import com.example.travelmap.domain.model.Country

data class LoginRequest(
    val email: String,
    val password: String
)

data class RegisterRequest(
    val name: String,
    val email: String,
    val password: String
)

data class CountryRequest(
    val name: String,
    val longitude: Double,
    val latitude: Double
)

data class MessageRequest(
    val content: String
)

data class UpdateMessageRequest(
    val isSaved: Boolean
)

data class UpdateUserRequest(
    val name: String
)
