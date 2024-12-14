package com.example.travelmap.presentation.welcome

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.travelmap.domain.usecases.SetFirstLaunchCompletedUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WelcomeViewModel @Inject constructor (
    private val setFirstLaunchCompletedUseCase: SetFirstLaunchCompletedUseCase
): ViewModel() {

    fun completeOnBoarding(){
        viewModelScope.launch {
            setFirstLaunchCompletedUseCase()
        }
    }
}