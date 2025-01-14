package com.example.travelmap.data.remote.api

import com.example.travelmap.data.remote.AuthResponse
import com.example.travelmap.data.remote.LoginRequest
import com.example.travelmap.data.remote.RegisterRequest
import com.example.travelmap.data.remote.UpdateMessageRequest
import com.example.travelmap.data.remote.UpdateUserRequest
import com.example.travelmap.data.remote.UserResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface AuthApi {
    @POST("/account/sign-in")
    suspend fun login(@Body loginRequest: LoginRequest): Response<AuthResponse>
    @POST("/account/sign-up")
    suspend fun register(@Body registerRequest: RegisterRequest): Response<AuthResponse>
    @GET("/account")
    suspend fun getUser(): Response<UserResponse>
    @PUT("/account")
    suspend fun updateUser(
        @Body updateRequest: UpdateUserRequest
    ): Response<UserResponse>
    @POST("/account/sign-out")
    suspend fun logOutUser(): Result<Unit>

}