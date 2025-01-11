package com.example.travelmap.domain.usecase.message

import com.example.travelmap.domain.model.Message
import com.example.travelmap.domain.repository.MessageRepository
import javax.inject.Inject

class GetListMessagesUseCase @Inject constructor(
    private val messageRepository: MessageRepository
) {
    suspend operator fun invoke(): List<Message>{
        return messageRepository.getListMessages()
    }
}