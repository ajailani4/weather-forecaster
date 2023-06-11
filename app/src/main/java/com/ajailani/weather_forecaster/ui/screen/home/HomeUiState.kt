package com.ajailani.weather_forecaster.ui.screen.home

import com.ajailani.weather_forecaster.domain.model.WeatherInfo

data class HomeUiState(
    val loading: Boolean? = false,
    val weatherInfo: WeatherInfo? = null,
    val errorMessage: String? = null
)