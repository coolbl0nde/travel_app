package com.example.travelmap.domain.usecase.message

import com.example.travelmap.domain.model.Message
import com.example.travelmap.domain.repository.MessageRepository
import javax.inject.Inject

class PostMessageUseCase @Inject constructor(
    private val messageRepository: MessageRepository
) {
    suspend operator fun invoke(content: String): Message {
        return messageRepository.postMessage(content)
    }
}