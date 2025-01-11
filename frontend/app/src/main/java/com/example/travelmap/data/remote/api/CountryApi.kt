package com.example.travelmap.data.remote.api

import com.example.travelmap.data.remote.CountryRequest
import com.example.travelmap.data.remote.CountryResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface CountryApi {
    @POST("/countries")
    suspend fun postCountry(@Body countryRequest: CountryRequest): Response<Unit>
    @GET("/countries")
    suspend fun getUserCountries(): List<CountryResponse>
}