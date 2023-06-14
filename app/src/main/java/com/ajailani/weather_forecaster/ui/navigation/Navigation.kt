package com.ajailani.weather_forecaster.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.ajailani.weather_forecaster.ui.screen.home.HomeScreen
import com.ajailani.weather_forecaster.ui.screen.home.HomeViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun Navigation(
    navController: NavHostController,
    startDestination: String
) {
    NavHost(navController = navController, startDestination = startDestination) {
        composable(Screen.HomeScreen.route) {
            val homeViewModel = koinViewModel<HomeViewModel>()
            val onEvent = homeViewModel::onEvent
            val homeUiState = homeViewModel.homeUiState

            HomeScreen(
                onEvent = onEvent,
                homeUiState = homeUiState
            )
        }

        composable(Screen.SearchLocationScreen.route) {

        }
    }
}