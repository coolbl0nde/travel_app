package com.example.travelmap.data.repository

import android.util.Log
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

    override suspend fun isLoggedIn(): Result<Unit> {
        return try {
            val token = tokenProvider.getToken()
            if (token != null) {
                Log.e("Auth Rep", "Token is success")
                Result.success(Unit)
            } else {
                Log.e("Auth Rep", "Token is missing")
                Result.failure(Exception("Token is missing"))
            }
        } catch (e: Exception) {
            Log.e("Auth Rep", "$e")
            Result.failure(e)
        }
    }

    override suspend fun getUser(): User {
        return try {
            val result = authRemoteDataSource.getUser()

            User(
                id = result.id,
                name = result.name,
                email = result.email
            )
        } catch (e: Exception) {
            Log.e("Auth Rep", "Error with getUser $e")
            throw e
        }
    }

    override suspend fun updateUser(name: String): User {
        return try {
            val result = authRemoteDataSource.updateUser(name)

            User(
                id = result.id,
                name = result.name,
                email = result.email
            )
        } catch (e: Exception) {
            Log.e("Auth Rep", "Error with updateUser $e")
            throw e
        }
    }

    override suspend fun logOutUser(): Result<Unit> {
        return try {

            authRemoteDataSource.logOutUser()

            tokenProvider.clearToken()
            Log.i("Auth Rep", "User successfully logged out")
            Result.success(Unit)

        } catch (e: Exception) {
            Log.e("Auth Rep", "Error during log out: $e")
            Result.failure(e)
        }
    }
}