package com.example.travelmap.domain.usecase.auth

import com.example.travelmap.domain.repository.AuthRepository
import javax.inject.Inject

class CheckAuthUseCase @Inject constructor(
    private val authRepository: AuthRepository
) {
    suspend operator fun invoke(): Result<Unit>{
        return authRepository.isLoggedIn()
    }
}