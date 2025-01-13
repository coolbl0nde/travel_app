package com.example.travelmap.domain.model

enum class MessageRole {
    user,
    assistant
}

data class Message (
    val id: Int,
    val content: String,
    val isSaved: Boolean,
    val role: MessageRole
)