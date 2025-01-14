package com.example.travelmap.domain.usecase.auth

import com.example.travelmap.domain.model.User
import com.example.travelmap.domain.repository.AuthRepository
import javax.inject.Inject

class GetUserUseCase @Inject constructor(
    private val authRepository: AuthRepository
) {
    suspend operator fun invoke(): User {
        return authRepository.getUser()
    }
}