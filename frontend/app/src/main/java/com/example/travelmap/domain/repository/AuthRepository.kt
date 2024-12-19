package com.example.travelmap.domain.repository

interface AuthRepository {

    suspend fun logInUser(email: String, password: String) : Result<String>

    suspend fun registerUser(name: String, email: String, password: String) : Result<String>
}