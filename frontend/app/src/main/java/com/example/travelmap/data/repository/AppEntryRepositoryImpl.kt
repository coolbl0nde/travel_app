package com.example.travelmap.data.repository

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.booleanPreferencesKey
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import com.example.travelmap.domain.repository.AppEntryRepository
import javax.inject.Inject


class AppEntryRepositoryImpl(
    private val context: Context
): AppEntryRepository {

    val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "app_preferences")

    private val IS_FIRST_LAUNCH = booleanPreferencesKey("is_first_launch")

    override suspend fun isFirstLaunch(): Boolean {
        return context.dataStore.data.map { prefs ->
            prefs[IS_FIRST_LAUNCH] ?: true
        }.first()
    }

    override suspend fun setFirstLaunchCompleted() {
        context.dataStore.edit { prefs ->
            prefs[IS_FIRST_LAUNCH] = false
        }
    }
}