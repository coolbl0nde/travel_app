package com.example.travelmap.data.local.prefs

import android.content.SharedPreferences
import javax.inject.Inject

class SharedPreferencesTokenProvider @Inject constructor(
    private val encryptedSharedPreferences: SharedPreferences
): TokenProvider {

    companion object{
        private const val KEY_TOKEN = "auth_token"
    }

    override fun getToken(): String? {
        return encryptedSharedPreferences.getString(KEY_TOKEN, null)
    }

    override fun saveToken(token: String) {
        encryptedSharedPreferences.edit().putString(KEY_TOKEN, token)
    }

    override fun clearToken() {
        encryptedSharedPreferences.edit().remove(KEY_TOKEN).apply()
    }
}