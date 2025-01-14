package com.example.travelmap.domain.usecase.auth

import com.example.travelmap.domain.model.User
import com.example.travelmap.domain.repository.AuthRepository
import javax.inject.Inject

class UpdateUserUseCase @Inject constructor(
    private val authRepository: AuthRepository
) {
    suspend operator fun invoke(name: String): User {
        return authRepository.updateUser(name)
    }
}