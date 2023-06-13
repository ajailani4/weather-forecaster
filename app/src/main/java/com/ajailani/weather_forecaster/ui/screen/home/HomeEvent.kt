package com.ajailani.weather_forecaster.ui.screen.home

sealed class HomeEvent {
    object SyncWeatherInfo : HomeEvent()
    data class OnSwipeRefresh(val isRefreshing: Boolean) : HomeEvent()
}
