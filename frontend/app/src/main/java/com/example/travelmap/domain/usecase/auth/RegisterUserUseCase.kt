package com.example.travelmap.domain.usecase.auth

import com.example.travelmap.domain.model.User
import com.example.travelmap.domain.repository.AuthRepository
import javax.inject.Inject

class RegisterUserUseCase @Inject constructor(
    private val authRepository: AuthRepository
) {
    suspend operator fun invoke(name: String, email: String, password: String): User{
        return authRepository.registerUser(name, email, password)
    }

}