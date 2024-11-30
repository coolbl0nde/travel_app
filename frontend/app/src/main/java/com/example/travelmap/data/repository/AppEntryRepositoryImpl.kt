package com.example.travelmap.data.repository

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.booleanPreferencesKey
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import com.example.travelmap.domain.repository.AppEntryRepository


class AppEntryRepositoryImpl(
    private val dataStore: DataStore<Preferences>
): AppEntryRepository {

    private val IS_FIRST_LAUNCH = booleanPreferencesKey("is_first_launch")

    override suspend fun isFirstLaunch(): Boolean {
        return dataStore.data.map { prefs ->
            prefs[IS_FIRST_LAUNCH] ?: true
        }.first()
    }

    override suspend fun setFirstLaunchCompleted() {
        dataStore.edit { prefs ->
            prefs[IS_FIRST_LAUNCH] = false
        }
    }
}