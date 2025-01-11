package com.example.travelmap.domain.usecase.message

import com.example.travelmap.domain.model.Message
import com.example.travelmap.domain.repository.MessageRepository
import javax.inject.Inject

class UpdateMessageUseCase @Inject constructor(
    private val messageRepository: MessageRepository
) {
    suspend operator fun invoke(message: Message){
        messageRepository.updateMessage(message)
    }
}