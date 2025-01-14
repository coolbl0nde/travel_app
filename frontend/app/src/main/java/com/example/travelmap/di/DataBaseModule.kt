package com.example.travelmap.di

import android.app.Application
import androidx.room.Room
import com.example.travelmap.data.local.db.AppDatabase
import com.example.travelmap.data.local.db.UserDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataBaseModule {

    @Provides
    @Singleton
    fun provideAppDatabase(
        application: Application
    ): AppDatabase {
        return Room.databaseBuilder(
            application,
            AppDatabase::class.java,
            "app_database"
        ).build()
    }

    @Provides
    @Singleton
    fun provideUserDao(
        database: AppDatabase
    ) : UserDao {
        return database.userDao()
    }
}