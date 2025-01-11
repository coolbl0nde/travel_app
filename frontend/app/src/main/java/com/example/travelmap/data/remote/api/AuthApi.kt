package com.example.travelmap.data.remote.api

import com.example.travelmap.data.remote.AuthResponse
import com.example.travelmap.data.remote.LoginRequest
import com.example.travelmap.data.remote.RegisterRequest
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthApi {
    @POST("account/sign-in")
    suspend fun login(@Body loginRequest: LoginRequest): Response<AuthResponse>
    @POST("account/sign-up")
    suspend fun register(@Body registerRequest: RegisterRequest): Response<AuthResponse>
}