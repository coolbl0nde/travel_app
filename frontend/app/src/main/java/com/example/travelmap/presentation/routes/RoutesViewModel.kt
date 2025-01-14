package com.example.travelmap.presentation.routes

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.travelmap.domain.model.Message
import com.example.travelmap.domain.usecase.message.getFavoriteMessagesListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RoutesViewModel @Inject constructor(
    private val getFavoriteMessagesListUseCase: getFavoriteMessagesListUseCase
): ViewModel() {

    private val _routes = MutableStateFlow<List<Message>>(emptyList())
    val routes: StateFlow<List<Message>> = _routes

    init {
        fetchRoutes()
    }

    private fun fetchRoutes() {
        viewModelScope.launch {
            try {
                val result = getFavoriteMessagesListUseCase()
                _routes.value = result
            } catch (e: Exception) {
                Log.e("RoutesViewModel", "Error with fetchRoutes: $e")
            }
        }
    }


}