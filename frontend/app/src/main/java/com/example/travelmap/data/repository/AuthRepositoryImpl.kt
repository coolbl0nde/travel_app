package com.example.travelmap.data.repository

import com.example.travelmap.data.local.db.UserDao
import com.example.travelmap.domain.repository.AuthRepository
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val userDao: UserDao
) : AuthRepository {

    override suspend fun logInUser(email: String, password: String): Result<String> {
        TODO("Not yet implemented")

    }

    override suspend fun registerUser(
        name: String,
        email: String,
        password: String
    ): Result<String> {
        TODO("Not yet implemented")
    }
}