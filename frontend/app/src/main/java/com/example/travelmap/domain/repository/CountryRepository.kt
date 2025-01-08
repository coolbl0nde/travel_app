package com.example.travelmap.domain.repository

import com.example.travelmap.domain.model.Country

interface CountryRepository {
    suspend fun getListCountries(query: String): List<Country>
    suspend fun postCountry(country: Country)
}