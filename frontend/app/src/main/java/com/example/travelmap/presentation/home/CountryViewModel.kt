package com.example.travelmap.presentation.home

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.travelmap.domain.model.Country
import com.example.travelmap.domain.usecase.country.GetListCountriesUseCase
import com.example.travelmap.domain.usecase.country.GetUserCountriesUseCase
import com.example.travelmap.domain.usecase.country.PostCountryUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CountryViewModel @Inject constructor(
    private val getListCountriesUseCase: GetListCountriesUseCase,
    private val postCountryUseCase: PostCountryUseCase,
    private val getUserCountriesUseCase: GetUserCountriesUseCase
): ViewModel() {

    private val _countries = MutableStateFlow<List<Country>>(emptyList())
    val countries: StateFlow<List<Country>> = _countries

    private val _searchText = MutableStateFlow("")
    val searchText: StateFlow<String> = _searchText.asStateFlow()

    private val _isExpanded = MutableStateFlow(false)
    val isExpanded: StateFlow<Boolean> = _isExpanded.asStateFlow()

    private val _userCountries = MutableStateFlow<List<Country>>(emptyList())
    val userCountries: StateFlow<List<Country>> = _userCountries

    init {
        fetchCountries(_searchText.value)
        getUserCountries()
    }

    fun update(){
        getUserCountries()
    }

    private fun getUserCountries (){
        viewModelScope.launch {
            try {
                val countries = getUserCountriesUseCase()
                _userCountries.value = countries
            }catch (e: Exception){
                Log.e("Country VM", "Country get error, $e")
            }
        }
    }

    fun updateSearchText(newText: String) {
        _searchText.value = newText
        fetchCountries(newText)
    }

    fun toggleExpanded(){
        _isExpanded.value = !_isExpanded.value
    }

    fun performSearch(){
        fetchCountries(_searchText.value)
        _isExpanded.value = false
    }


    fun addCountry(country: Country) {
        viewModelScope.launch {
            try {
                postCountryUseCase(country)
                getUserCountries()
            } catch (e: Exception){
                Log.e("Country VM", "Country add error, $e")
            }
        }
    }

    private fun fetchCountries(query: String) {
        viewModelScope.launch {
            try {
                val result = getListCountriesUseCase(query)
                _countries.value = result
            } catch (e: Exception) {
                Log.e("COUNTRY", "Ошибка при загрузке стран: $e")
            }
        }
    }

}