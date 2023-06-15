package com.ajailani.weather_forecaster.ui.screen.search_location

import com.ajailani.weather_forecaster.domain.model.Location

data class SearchLocationUiState(
    val loading: Boolean = false,
    val query: String = "",
    val locations: List<Location> = emptyList(),
    val errorMessage: String? = null
)
