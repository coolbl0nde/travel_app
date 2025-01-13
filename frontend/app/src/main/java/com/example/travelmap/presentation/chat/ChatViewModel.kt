package com.example.travelmap.presentation.chat

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.travelmap.domain.model.Message
import com.example.travelmap.domain.model.MessageRole
import com.example.travelmap.domain.usecase.message.GetListMessagesUseCase
import com.example.travelmap.domain.usecase.message.PostMessageUseCase
import com.example.travelmap.domain.usecase.message.UpdateMessageUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ChatViewModel @Inject constructor(
    private val getListMessagesUseCase: GetListMessagesUseCase,
    private val postMessageUseCase: PostMessageUseCase,
    private val updateMessageUseCase: UpdateMessageUseCase
): ViewModel() {

    private val _messages = MutableStateFlow<List<Message>>(emptyList())
    val messages: StateFlow<List<Message>> = _messages

    private val _isAnswerLoading = MutableStateFlow(false)
    var isAnswerLoading: StateFlow<Boolean> = _isAnswerLoading

    init {
        fetchMessages()
    }

    private fun fetchMessages() {
        viewModelScope.launch {
            try {
                val result = getListMessagesUseCase()
                _messages.value = result
            } catch (e: Exception) {
                Log.e("ChatViewModel", "Error with fetchMessages: $e")
            }
        }
    }

    fun addMessage(content: String) {
        viewModelScope.launch{
            try {
                _isAnswerLoading.value = true

                val userMessage = Message(
                    id = 0,
                    content = content,
                    isSaved = false,
                    role = MessageRole.user
                )
                _messages.value = _messages.value.toMutableList().apply { add(userMessage) }

                val consultantAnswer = postMessageUseCase(content)
                _messages.value = _messages.value.toMutableList().apply { add(consultantAnswer) }

                _isAnswerLoading.value = false
            } catch (e: Exception) {
                Log.e("ChatViewModel", "Error with addMessage: $e")
            }
        }
    }

    fun updateMessage(id: Int, isSaved: Boolean){
        viewModelScope.launch {
            try {
                updateMessageUseCase(id, isSaved)
                _messages.update { currentMessages ->
                    currentMessages.map { message ->
                        if (message.id == id) message.copy(isSaved = isSaved) else message
                    }
                }
            } catch (e: Exception) {
                Log.e("ChatViewModel", "Error with updateMessage: $e")
            }
        }
    }
}