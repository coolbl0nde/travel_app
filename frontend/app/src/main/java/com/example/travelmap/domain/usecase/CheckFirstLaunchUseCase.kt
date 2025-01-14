package com.example.travelmap.domain.usecase

import com.example.travelmap.domain.repository.AppEntryRepository

class CheckFirstLaunchUseCase(private val repository: AppEntryRepository) {
    suspend operator fun invoke(): Boolean {
        return repository.isFirstLaunch()
    }
}