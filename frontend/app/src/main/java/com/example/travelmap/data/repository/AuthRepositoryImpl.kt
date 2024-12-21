package com.example.travelmap.data.repository

import com.example.travelmap.data.local.db.UserDao
import com.example.travelmap.data.local.prefs.TokenProvider
import com.example.travelmap.data.remote.AuthRemoteDataSource
import com.example.travelmap.domain.model.User
import com.example.travelmap.domain.repository.AuthRepository
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val authRemoteDataSource: AuthRemoteDataSource,
    private val tokenProvider: TokenProvider,
    private val userDao: UserDao
) : AuthRepository {

    override suspend fun logInUser(email: String, password: String): User {
        val loginResponse = authRemoteDataSource.login(email, password)

        tokenProvider.saveToken(loginResponse.accessToken)

        return loginResponse.user
    }

    override suspend fun registerUser(
        name: String,
        email: String,
        password: String
    ): User {
        val registerResponse = authRemoteDataSource.register(name, email, password)

        tokenProvider.saveToken(registerResponse.accessToken)

        return registerResponse.user
    }
}