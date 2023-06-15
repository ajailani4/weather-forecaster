package com.ajailani.weather_forecaster.ui.navigation

sealed class Screen(val route: String) {
    object Home : Screen("home_screen")
    object SearchLocation : Screen("search_location_screen")
}
