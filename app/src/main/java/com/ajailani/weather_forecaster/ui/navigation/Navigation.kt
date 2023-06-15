package com.ajailani.weather_forecaster.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import com.ajailani.weather_forecaster.ui.screen.home.HomeScreen
import com.ajailani.weather_forecaster.ui.screen.home.HomeViewModel
import com.ajailani.weather_forecaster.ui.screen.search_location.SearchLocationScreen
import com.ajailani.weather_forecaster.ui.screen.search_location.SearchLocationViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun Navigation(
    navController: NavHostController,
    startDestination: String
) {
    NavHost(navController = navController, startDestination = startDestination) {
        composable(Screen.Home.route) {
            val homeViewModel = koinViewModel<HomeViewModel>()
            val onEvent = homeViewModel::onEvent
            val homeUiState = homeViewModel.homeUiState

            HomeScreen(
                onEvent = onEvent,
                homeUiState = homeUiState,
                onNavigateToSearchLocation = {
                    navController.navigate(Screen.SearchLocation.route)
                }
            )
        }

        composable(Screen.SearchLocation.route) {
            val searchLocationViewModel = koinViewModel<SearchLocationViewModel>()
            val onEvent = searchLocationViewModel::onEvent
            val searchLocationUiState = searchLocationViewModel.searchLocationUiState

            SearchLocationScreen(
                onEvent = onEvent,
                searchLocationUiState = searchLocationUiState,
                isFirstTimeUserHere = navController.previousBackStackEntry == null,
                onNavigateUp = { navController.navigateUp() },
                onNavigateToHome = {
                    navController.navigate(Screen.Home.route) {
                        launchSingleTop = true

                        popUpTo(Screen.SearchLocation.route) {
                            inclusive = true
                        }
                    }
                }
            )
        }
    }
}