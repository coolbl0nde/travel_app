package com.example.travelmap.domain.usecases

import com.example.travelmap.domain.repository.AppEntryRepository

class SetFirstLaunchCompletedUseCase(
    private val repository: AppEntryRepository
) {
    suspend operator fun invoke() {
        repository.setFirstLaunchCompleted()
    }

}