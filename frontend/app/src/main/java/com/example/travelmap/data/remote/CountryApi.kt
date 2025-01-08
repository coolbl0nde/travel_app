package com.example.travelmap.data.remote

import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface CountryApi {
    @POST("/countries")
    suspend fun postCountry(@Body countryRequest: CountryRequest): Response<Unit>
}