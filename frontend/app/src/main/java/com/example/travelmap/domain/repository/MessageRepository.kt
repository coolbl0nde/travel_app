package com.example.travelmap.domain.repository

import com.example.travelmap.domain.model.Message

interface MessageRepository {
    suspend fun getListMessages(): List<Message>
    suspend fun postMessage(content: String)
    suspend fun updateMessage(message: Message)
}