package com.ajailani.weather_forecaster

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.lifecycle.lifecycleScope
import androidx.navigation.compose.rememberNavController
import com.ajailani.weather_forecaster.ui.common.MainViewModel
import com.ajailani.weather_forecaster.ui.navigation.Navigation
import com.ajailani.weather_forecaster.ui.navigation.Screen
import com.ajailani.weather_forecaster.ui.theme.WeatherForecasterTheme
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : ComponentActivity() {
    private val mainViewModel: MainViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        lifecycleScope.launch {
            val startDestination = if (mainViewModel.getLocationName().first().isNotEmpty()) {
                Screen.Home.route
            } else {
                Screen.SearchLocation.route
            }

            setContent {
                WeatherForecasterTheme {
                    Surface(
                        modifier = Modifier.fillMaxSize(),
                        color = MaterialTheme.colorScheme.background
                    ) {
                        val navController = rememberNavController()

                        Navigation(
                            navController = navController,
                            startDestination = startDestination
                        )
                    }
                }
            }
        }
    }
}
