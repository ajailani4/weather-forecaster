package com.ajailani.weather_forecaster

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.ajailani.weather_forecaster.ui.navigation.Navigation
import com.ajailani.weather_forecaster.ui.navigation.Screen
import com.ajailani.weather_forecaster.ui.theme.WeatherForecasterTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            WeatherForecasterTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()

                    Navigation(
                        navController = navController,
                        startDestination = Screen.SearchLocation.route
                    )
                }
            }
        }
    }
}
