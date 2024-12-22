package com.example.travelmap.presentation.auth.register

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
class RegisterViewModel @Inject constructor(
    private val registerUseCase: RegisterUserUseCase,
): ViewModel() {

    private val _isSuccess = MutableStateFlow<Boolean>(false)
    val isSuccess: StateFlow<Boolean> = _isSuccess

    fun onRegisterClicked(name: String, email: String, password: String){

        viewModelScope.launch {
            try {
                val user = registerUseCase(name, email, password)
                _isSuccess.value = true
            } catch (e: Exception){
                Log.e("MSG", "Register error, ${e.toString()}")
            }
        }

    }
}