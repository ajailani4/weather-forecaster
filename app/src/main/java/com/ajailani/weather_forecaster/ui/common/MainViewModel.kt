package com.ajailani.weather_forecaster.ui.common

import androidx.lifecycle.ViewModel
import com.ajailani.weather_forecaster.domain.use_case.GetLocationNameUseCase

class MainViewModel(
    private val getLocationNameUseCase: GetLocationNameUseCase
) : ViewModel() {
    fun getLocationName() = getLocationNameUseCase()
}