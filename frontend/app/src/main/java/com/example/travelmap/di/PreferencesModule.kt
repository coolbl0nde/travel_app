package com.example.travelmap.di

import android.content.Context
import android.content.SharedPreferences
import android.util.Log
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKey
import com.example.travelmap.data.local.prefs.SharedPreferencesTokenProvider
import com.example.travelmap.data.local.prefs.TokenProvider
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.crypto.AEADBadTagException
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object PreferencesModule {

    @Provides
    @Singleton
    fun provideMasterKey(@ApplicationContext context: Context): MasterKey {
        return MasterKey.Builder(context)
            .setKeyScheme(MasterKey.KeyScheme.AES256_GCM)
            .build()
    }

    @Provides
    @Singleton
    fun provideEncryptedSharedPreferences(
        @ApplicationContext context: Context,
        masterKey: MasterKey
    ): SharedPreferences {

        return try {
            val sharedPreferences = EncryptedSharedPreferences.create(
                context,
                "encrypted_prefs",
                masterKey,
                EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
                EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
            )
            sharedPreferences
        } catch (e: AEADBadTagException) {
            Log.e("EncryptedPrefs", "Data is corrupted, clearing preferences")
            clearEncryptedSharedPreferences(context)
            val sharedPreferences = EncryptedSharedPreferences.create(
                context,
                "encrypted_prefs",
                masterKey,
                EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
                EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
            )
            sharedPreferences
        }

        /*return EncryptedSharedPreferences.create(
            context,
            "encrypted_prefs",
            masterKey,
            EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
            EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
        )*/
    }

    private fun clearEncryptedSharedPreferences(context: Context) {
        val sharedPreferences = context.getSharedPreferences("encrypted_prefs", Context.MODE_PRIVATE)
        sharedPreferences.edit().clear().apply()
    }

    @Provides
    @Singleton
    fun provideTokenProvider(
        encryptedSharedPreferences: SharedPreferences
    ): TokenProvider {
        return SharedPreferencesTokenProvider(encryptedSharedPreferences)
    }
}