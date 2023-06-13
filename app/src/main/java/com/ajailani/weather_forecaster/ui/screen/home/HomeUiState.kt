package com.ajailani.weather_forecaster.ui.screen.home

import com.ajailani.weather_forecaster.domain.model.WeatherInfo

data class HomeUiState(
    val weatherInfo: WeatherInfo? = null,
    val isRefreshing: Boolean = false,
    val errorMessage: String? = null
)
