package com.example.travelmap.data.repository

import android.util.Log
import com.example.travelmap.data.remote.CountryApi
import com.example.travelmap.data.remote.CountryRequest
import com.example.travelmap.domain.model.Country
import com.example.travelmap.domain.repository.CountryRepository
import com.google.android.libraries.places.api.model.Place
import com.google.android.libraries.places.api.net.FetchPlaceRequest
import com.google.android.libraries.places.api.net.FindAutocompletePredictionsRequest
import com.google.android.libraries.places.api.net.PlacesClient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext
import javax.inject.Inject

class CountryRepositoryImpl @Inject constructor(
    private val placesClient: PlacesClient,
    private val countryApi: CountryApi
): CountryRepository {

    override suspend fun getListCountries(query: String): List<Country> = withContext(Dispatchers.IO){
        val request = FindAutocompletePredictionsRequest.builder()
            .setQuery(query)
            .setTypesFilter(listOf("country"))
            .build()

        val response = placesClient.findAutocompletePredictions(request).await()
        response.autocompletePredictions.mapNotNull { prediction ->
            val placeId = prediction.placeId

            val placeRequest = FetchPlaceRequest.builder(placeId, listOf(Place.Field.LAT_LNG,
                Place.Field.NAME))
                .build()

            val placeResponse = placesClient.fetchPlace(placeRequest).await()
            val place = placeResponse.place

            val latLng = place.latLng
            if (latLng != null) {
                Country(
                    name = place.name ?: prediction.getPrimaryText(null).toString(),
                    latitude = latLng.latitude,
                    longitude = latLng.longitude
                )
            } else {
                null
            }
        }
    }

    override suspend fun postCountry(country: Country) {
        try {
            val request = CountryRequest(
                name = country.name,
                longitude = country.longitude,
                latitude = country.latitude
            )

            val response = countryApi.postCountry(request)

            if (response.isSuccessful){
                Log.d("CountryRepository", "Успешно отправлено!")
            } else {
                val errorMessage = response.errorBody()?.string() ?: "Ошибка запроса"
                Log.e("CountryRepository", "Ошибка: $errorMessage")
            }

        } catch (e: Exception) {
            Log.e("CountryRepository", "Ошибка сети или сервера: ${e.message}", e)
        }
    }

    override suspend fun getUserCountries(): List<Country> {
        return try {
            val response = countryApi.getUserCountries()

            response.map { countryResponse ->
                Country(
                    id = countryResponse.id,
                    name = countryResponse.name,
                    latitude = countryResponse.latitude,
                    longitude = countryResponse.longitude
                )
            }

        } catch (e: Exception) {
            Log.e("CountryRepository", "Ошибка сети или сервера: ${e.message}", e)
            emptyList()
        }
    }
}