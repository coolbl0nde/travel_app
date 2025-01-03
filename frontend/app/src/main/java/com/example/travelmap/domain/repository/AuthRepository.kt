package com.example.travelmap.domain.repository

import com.example.travelmap.domain.model.User

interface AuthRepository {

    suspend fun logInUser(email: String, password: String) : User

    suspend fun registerUser(name: String, email: String, password: String) : User
}