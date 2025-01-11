package com.example.travelmap.data.remote

import com.example.travelmap.domain.model.Country
import com.example.travelmap.domain.model.MessageRole
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

data class CountryResponse(
    val id: String,
    val name: String,
    val latitude: Double,
    val longitude: Double
)

data class MessageResponse(
    val id: Int,
    val userId: Int,
    val content: String,
    val role: MessageRole,
    val isSaved: Boolean,
    val createdOn: String
)