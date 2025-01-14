package com.example.travelmap.presentation.main

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.travelmap.domain.usecase.auth.CheckAuthUseCase
import com.example.travelmap.domain.usecase.auth.LogOutUserUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val checkAuthUseCase: CheckAuthUseCase,
    private val logOutUserUseCase: LogOutUserUseCase
):ViewModel() {

    private val _startDestination = MutableStateFlow("")
    val startDestination: StateFlow<String> = _startDestination.asStateFlow()

    init {
        checkIfLoggedIn()
    }

    private fun checkIfLoggedIn() {
        viewModelScope.launch {
            try {
                val result = checkAuthUseCase()

                result.fold(
                    onSuccess = {
                        _startDestination.value = "home"
                    },
                    onFailure = { error ->
                        Log.e("MainViewModel", "Ошибка проверки авторизации: ${error.message}")
                        _startDestination.value = "auth"
                    }
                )
            } catch (e: Exception) {
                Log.e("MainViewModel", "Непредвиденная ошибка: ${e.message}")
                _startDestination.value = "auth"
            }
        }
    }

    fun logOutUser() {
        viewModelScope.launch {
            try {
                logOutUserUseCase()
                _startDestination.value = "auth"
            } catch (e: Exception) {
                Log.e("MainViewModel", "Ошибка при выходе: ${e.message}")
            }
        }
    }
}