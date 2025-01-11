package com.example.travelmap.data.repository

import android.util.Log
import com.example.travelmap.data.remote.CountryRequest
import com.example.travelmap.data.remote.MessageRequest
import com.example.travelmap.data.remote.api.CountryApi
import com.example.travelmap.data.remote.api.MessageApi
import com.example.travelmap.domain.model.Country
import com.example.travelmap.domain.model.Message
import com.example.travelmap.domain.model.MessageRole
import com.example.travelmap.domain.repository.MessageRepository
import javax.inject.Inject

class MessageRepositoryImpl @Inject constructor(
    private val messageApi: MessageApi
): MessageRepository {
    override suspend fun getListMessages(): List<Message> {
        return try {
            val response = messageApi.getListMessages()

            response.map { messageResponse ->
                Message(
                    id = messageResponse.id,
                    content = messageResponse.content,
                    isSaved  = messageResponse.isSaved,
                    role = messageResponse.role
                )
            }
        } catch (e: Exception) {
            Log.e("MessageRepository", "Error with getListMessages $e")
            emptyList()
        }
    }

    override suspend fun postMessage(content: String) {
        try {
            val request = MessageRequest(
                content = content
            )

            val response = messageApi.postMessage(request)

            if (response.isSuccessful){
                Log.d("MessageRepository", "Успешно отправлено!")
            } else {
                val errorMessage = response.errorBody()?.string() ?: "Ошибка запроса"
                Log.e("MessageRepository", "Ошибка: $errorMessage")
            }
        } catch (e: Exception) {
            Log.e("MessageRepository", "Error with postMessage $e")
        }
    }

    override suspend fun updateMessage(message: Message) {
        TODO("Not yet implemented")
    }
}