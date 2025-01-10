package com.example.travelmap.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.travelmap.domain.model.Country
import com.example.travelmap.domain.usecase.country.GetUserCountriesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getUserCountriesUseCase: GetUserCountriesUseCase
): ViewModel() {

    private val _userCountries = MutableStateFlow<List<Country>>(emptyList())
    val userCountries: StateFlow<List<Country>> = _userCountries

    private val _userCountriesAmount = MutableStateFlow<String>("0")
    val userCountriesAmount: StateFlow<String> = _userCountriesAmount

    init {
        getUserCountries()
    }

    private fun getUserCountries (){
        viewModelScope.launch {
            val countries = getUserCountriesUseCase()

            _userCountries.value = countries
        }
    }
}