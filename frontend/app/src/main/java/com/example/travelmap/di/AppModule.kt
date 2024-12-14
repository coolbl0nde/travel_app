package com.example.travelmap.di

import android.app.Application
import com.example.travelmap.data.repository.AppEntryRepositoryImpl
import com.example.travelmap.domain.repository.AppEntryRepository
import com.example.travelmap.domain.usecases.SetFirstLaunchCompletedUseCase
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
    fun providesSetFirstLaunchCompletedUseCase(
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