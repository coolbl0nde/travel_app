package com.example.travelmap.domain.repository

interface AppEntryRepository {
    suspend fun isFirstLaunch(): Boolean
    suspend fun setFirstLaunchCompleted()
}