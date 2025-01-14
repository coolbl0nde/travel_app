package com.example.travelmap.domain.repository

import com.example.travelmap.domain.model.Message

interface MessageRepository {
    suspend fun getListMessages(): List<Message>
    suspend fun postMessage(content: String): Message
    suspend fun updateMessage(id: Int, isSaved: Boolean)
    suspend fun getFavoriteMessagesList(): List<Message>
}