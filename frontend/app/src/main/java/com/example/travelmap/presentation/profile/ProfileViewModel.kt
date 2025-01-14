package com.example.travelmap.presentation.profile

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.travelmap.domain.model.User
import com.example.travelmap.domain.usecase.auth.GetUserUseCase
import com.example.travelmap.domain.usecase.auth.LogOutUserUseCase
import com.example.travelmap.domain.usecase.auth.UpdateUserUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val getUserUseCase: GetUserUseCase,
    private val updateUserUseCase: UpdateUserUseCase,
): ViewModel() {

    private val _user = MutableStateFlow(User())
    val user: StateFlow<User> = _user

    private val _logOutEvent = MutableStateFlow(false)
    val logOutEvent: StateFlow<Boolean> = _logOutEvent

    fun getUser(){
        viewModelScope.launch {
            try {
                val result = getUserUseCase()
                _user.value = result
            } catch (e: Exception) {
                throw e
            }
        }
    }

    fun updateUser(name: String){
        viewModelScope.launch {
            try {
                val result = updateUserUseCase(name)
                _user.value = result
            } catch (e: Exception) {
                throw e
            }
        }
    }

    fun logOut() {
        viewModelScope.launch {
            try {
                _logOutEvent.value = true
            } catch (e: Exception) {
                Log.e("ProfileViewModel", "Error during log out: $e")
            }
        }
    }

    fun resetLogOutEvent() {
        _logOutEvent.value = false
    }
}