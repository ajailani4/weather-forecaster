package com.ajailani.weather_forecaster.ui.screen.search_location

sealed class SearchLocationEvent {
    object GetLocations : SearchLocationEvent()
}