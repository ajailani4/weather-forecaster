package com.ajailani.weather_forecaster.ui.screen.search_location

import com.ajailani.weather_forecaster.domain.model.Location

sealed class SearchLocationEvent {
    object GetLocations : SearchLocationEvent()
    data class OnQueryChanged(val query: String) : SearchLocationEvent()
    data class SaveLocation(val location: Location) : SearchLocationEvent()
}