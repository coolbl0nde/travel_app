package com.example.travelmap.domain.usecase.country

import com.example.travelmap.domain.model.Country
import com.example.travelmap.domain.repository.CountryRepository
import javax.inject.Inject

class GetUserCountriesUseCase @Inject constructor(
    private val countryRepository: CountryRepository
) {
    suspend operator fun invoke(): List<Country> {
        return countryRepository.getUserCountries()
    }
}