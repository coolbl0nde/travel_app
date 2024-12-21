package com.example.travelmap.presentation.auth.login

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.travelmap.domain.usecase.auth.LogInUserUseCase
import com.example.travelmap.domain.usecase.auth.RegisterUserUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val logInUserUseCase: LogInUserUseCase
): ViewModel() {

    private val _isSuccess = MutableStateFlow<Boolean>(false)
    val isSuccess: StateFlow<Boolean> = _isSuccess

    fun onLoginClicked(email: String, password: String){

        viewModelScope.launch {
            try {
                val user = logInUserUseCase(email, password)
                _isSuccess.value = true
            } catch (e: Exception){
                Log.e("MSG", "Login error, ${e.toString()}")
            }
        }

    }
}