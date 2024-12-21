package com.example.travelmap.data.remote

import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthApi {

    @POST("account/sign-in")
    suspend fun login(@Body loginRequest: LoginRequest): Response<LoginResponse>

    @POST("account/sign-up")
    suspend fun register(@Body registerRequest: RegisterRequest): Response<RegisterResponse>
}