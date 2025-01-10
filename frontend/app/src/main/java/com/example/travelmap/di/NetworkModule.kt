package com.example.travelmap.di

import com.example.travelmap.data.local.prefs.TokenProvider
import com.example.travelmap.data.remote.AuthApi
import com.example.travelmap.data.remote.AuthInterceptor
import com.example.travelmap.data.remote.AuthRemoteDataSource
import com.example.travelmap.data.remote.CountryApi
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun provideOkHttpClient(
        tokenProvider: TokenProvider
    ): OkHttpClient {
        val authInterceptor = AuthInterceptor(tokenProvider)

        return OkHttpClient.Builder()
            .addNetworkInterceptor(authInterceptor)
            .build()
    }

    @Provides
    @Singleton
    fun provideMoshi(): Moshi {
        return Moshi.Builder().build()
    }

    @Provides
    @Singleton
    fun provideGson(): Gson {
        return GsonBuilder().create()
    }

    @Provides
    @Singleton
    fun provideRetrofit(
        okHttpClient: OkHttpClient,
        //moshi: Moshi
        gson: Gson
    ): Retrofit{
        return Retrofit.Builder()
            .baseUrl("https://239e-167-71-62-132.ngrok-free.app/")
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
    }

    @Provides
    @Singleton
    fun provideAuthApi(retrofit: Retrofit): AuthApi{
        return retrofit.create(AuthApi::class.java)
    }

    @Provides
    @Singleton
    fun provideAuthRemoteDataSource(authApi: AuthApi): AuthRemoteDataSource {
        return AuthRemoteDataSource(authApi)
    }

    @Provides
    @Singleton
    fun provideCountryApi(retrofit: Retrofit): CountryApi{
        return retrofit.create(CountryApi::class.java)
    }
}