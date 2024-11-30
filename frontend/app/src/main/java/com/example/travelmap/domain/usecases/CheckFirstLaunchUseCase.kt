package com.example.travelmap.domain.usecases

import com.example.travelmap.domain.repository.AppEntryRepository

class CheckFirstLaunchUseCase(private val repository: AppEntryRepository) {
    suspend operator fun invoke(): Boolean {
        return repository.isFirstLaunch()
    }
}