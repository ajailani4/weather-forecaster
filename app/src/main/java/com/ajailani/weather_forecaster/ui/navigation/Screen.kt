package com.ajailani.weather_forecaster.ui.navigation

sealed class Screen(val route: String) {
    object HomeScreen : Screen("home_screen")
    object SearchLocationScreen : Screen("search_location_screen")
}
