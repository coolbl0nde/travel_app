package com.example.travelmap.di

import android.app.Application
import android.content.Context
import com.example.travelmap.data.local.db.UserDao
import com.example.travelmap.data.local.prefs.TokenProvider
import com.example.travelmap.data.remote.AuthRemoteDataSource
import com.example.travelmap.data.remote.api.CountryApi
import com.example.travelmap.data.repository.AppEntryRepositoryImpl
import com.example.travelmap.data.repository.AuthRepositoryImpl
import com.example.travelmap.data.repository.CountryRepositoryImpl
import com.example.travelmap.domain.repository.AppEntryRepository
import com.example.travelmap.domain.repository.AuthRepository
import com.example.travelmap.domain.repository.CountryRepository
import com.example.travelmap.domain.usecase.SetFirstLaunchCompletedUseCase
import com.example.travelmap.domain.usecase.auth.LogInUserUseCase
import com.example.travelmap.domain.usecase.auth.RegisterUserUseCase
import com.google.android.libraries.places.api.Places
import com.google.android.libraries.places.api.net.PlacesClient
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
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

    @Provides
    @Singleton
    fun provideAuthRepository(
        authRemoteDataSource: AuthRemoteDataSource,
        tokenProvider: TokenProvider,
        userDao: UserDao
    ): AuthRepository {
        return AuthRepositoryImpl(authRemoteDataSource = authRemoteDataSource,
            tokenProvider = tokenProvider, userDao = userDao)
    }


    @Provides
    @Singleton
    fun provideLoginUserUseCase(authRepository: AuthRepositoryImpl): LogInUserUseCase {
        return LogInUserUseCase(authRepository)
    }

    @Provides
    @Singleton
    fun provideRegisterUserUseCase(authRepository: AuthRepositoryImpl): RegisterUserUseCase {
        return RegisterUserUseCase(authRepository)
    }

    @Provides
    @Singleton
    fun provideCountryRepository(
        placesClient: PlacesClient,
        countryApi: CountryApi
    ): CountryRepository {
        return CountryRepositoryImpl(placesClient, countryApi)
    }

    @Provides
    @Singleton
    fun providePlacesClient(@ApplicationContext context: Context): PlacesClient {
        return Places.createClient(context)
    }

}