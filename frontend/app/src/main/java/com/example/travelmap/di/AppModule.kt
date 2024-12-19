package com.example.travelmap.di

import android.app.Application
import androidx.room.Room
import com.example.travelmap.data.local.db.AppDatabase
import com.example.travelmap.data.local.db.UserDao
import com.example.travelmap.data.repository.AppEntryRepositoryImpl
import com.example.travelmap.domain.repository.AppEntryRepository
import com.example.travelmap.domain.usecase.SetFirstLaunchCompletedUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideSetFirstLaunchCompletedUseCase(
        repository: AppEntryRepository
    ): SetFirstLaunchCompletedUseCase{
        return SetFirstLaunchCompletedUseCase(repository)
    }

    @Provides
    @Singleton
    fun provideAppEntryRepository(
        application: Application
    ): AppEntryRepository {
        return AppEntryRepositoryImpl(context = application)
    }


}