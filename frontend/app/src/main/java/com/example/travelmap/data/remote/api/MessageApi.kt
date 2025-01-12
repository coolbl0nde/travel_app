package com.example.travelmap.data.remote.api

import com.example.travelmap.data.remote.MessageRequest
import com.example.travelmap.data.remote.MessageResponse
import com.example.travelmap.data.remote.UpdateMessageRequest
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface MessageApi {
    @POST("/messages")
    suspend fun postMessage(@Body messageRequest: MessageRequest): Response<Unit>
    @GET("/messages")
    suspend fun getListMessages(): List<MessageResponse>
    @PUT("/messages/{id}")
    suspend fun updateMessage(
        @Path("id") id: Int,
        @Body updateRequest: UpdateMessageRequest
    ): Response<Unit>
}