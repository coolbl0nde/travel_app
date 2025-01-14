package com.example.travelmap.data.remote

import android.util.Log
import com.example.travelmap.data.remote.api.AuthApi
import javax.inject.Inject

class AuthRemoteDataSource @Inject constructor(
    private val authApi: AuthApi
) {

    suspend fun login(email: String, password: String): AuthResponse {
        val response = authApi.login(LoginRequest(email, password))

        if (response.isSuccessful && response.body() != null) {
            return response.body()!!
        } else {
            throw Exception("Login failed")
        }
    }

    suspend fun register(name: String, email: String, password: String): AuthResponse {
        val response = authApi.register(RegisterRequest(name, email, password))

        if (response.isSuccessful && response.body() != null) {
            return response.body()!!
        } else {
            Log.e("EXC", "Login remote data source")
            throw Exception("Login failed")
        }
    }

    suspend fun getUser(): UserResponse {
        val response = authApi.getUser()

        if (response.isSuccessful && response.body() != null) {
            return response.body()!!
        } else {
            Log.e("EXC", "getUser")
            throw Exception("getUser failed")
        }
    }

    suspend fun updateUser(name: String): UserResponse {
        val response = authApi.updateUser(UpdateUserRequest(name))

        if (response.isSuccessful && response.body() != null) {
            return response.body()!!
        } else {
            Log.e("EXC", "updateUser")
            throw Exception("updateUser failed")
        }
    }

    suspend fun logOutUser(): Result<Unit> {
        return try {
            val response = authApi.logOutUser()
            response
        } catch (e: Exception) {
            throw e
        }
    }
}