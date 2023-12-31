package com.ajailani.weather_forecaster.ui.screen.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Air
import androidx.compose.material.icons.outlined.LocationOn
import androidx.compose.material.icons.outlined.Thermostat
import androidx.compose.material.icons.outlined.WaterDrop
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.ajailani.weather_forecaster.R
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.SwipeRefreshIndicator
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    onEvent: (HomeEvent) -> Unit,
    homeUiState: HomeUiState,
    onNavigateToSearchLocation: () -> Unit
) {
    val snackbarHostState = remember { SnackbarHostState() }

    Scaffold(
        snackbarHost = { SnackbarHost(hostState = snackbarHostState) }
    ) { innerPadding ->
        SwipeRefresh(
            state = rememberSwipeRefreshState(isRefreshing = homeUiState.isRefreshing),
            onRefresh = {
                onEvent(HomeEvent.OnSwipeRefresh(true))
                onEvent(HomeEvent.SyncWeatherInfo)
            },
            indicator = { state, trigger ->
                SwipeRefreshIndicator(
                    state = state,
                    refreshTriggerDistance = trigger,
                    contentColor = MaterialTheme.colorScheme.primary
                )
            }
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
                    .verticalScroll(rememberScrollState())
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(20.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Row(modifier = Modifier.clickable { onNavigateToSearchLocation() }) {
                        Icon(
                            imageVector = Icons.Outlined.LocationOn,
                            contentDescription = "Location icon"
                        )
                        Spacer(modifier = Modifier.width(3.dp))
                        Text(
                            text = homeUiState.locationName,
                            style = MaterialTheme.typography.titleMedium
                        )
                    }
                    Spacer(modifier = Modifier.height(30.dp))

                    homeUiState.apply {
                        when {
                            weatherInfo != null -> {
                                val weatherComposition by rememberLottieComposition(
                                    LottieCompositionSpec.RawRes(
                                        when (weatherInfo.weathers[0].main) {
                                            "Thunderstorm" -> R.raw.weather_thunderstorm
                                            "Drizzle" -> R.raw.weather_drizzle
                                            "Rain" -> R.raw.weather_rain
                                            "Snow" -> R.raw.weather_snow
                                            "Clear" -> R.raw.weather_clear
                                            "Clouds" -> R.raw.weather_clouds
                                            else -> R.raw.weather_atmosphere
                                        }
                                    )
                                )
                                val progress by animateLottieCompositionAsState(
                                    composition = weatherComposition,
                                    iterations = LottieConstants.IterateForever
                                )

                                LottieAnimation(
                                    composition = weatherComposition,
                                    progress = { progress }
                                )
                                Spacer(modifier = Modifier.height(20.dp))
                                Row {
                                    Text(
                                        text = "${weatherInfo.main.temp?.toInt()}",
                                        style = MaterialTheme.typography.displayLarge.copy(
                                            fontSize = 80.sp
                                        )
                                    )
                                    Text(
                                        text = "°C",
                                        style = MaterialTheme.typography.titleLarge
                                    )
                                }
                                Spacer(modifier = Modifier.height(10.dp))
                                Text(
                                    text = weatherInfo.weathers[0].main,
                                    style = MaterialTheme.typography.headlineMedium
                                )
                                Spacer(modifier = Modifier.height(5.dp))
                                Text(
                                    text = weatherInfo.weathers[0].description.replaceFirstChar(Char::titlecase),
                                    style = MaterialTheme.typography.bodyLarge
                                )
                                Spacer(modifier = Modifier.height(40.dp))
                                Card(modifier = Modifier.fillMaxWidth()) {
                                    Row(
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .padding(20.dp),
                                        horizontalArrangement = Arrangement.SpaceAround
                                    ) {
                                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                                            Icon(
                                                imageVector = Icons.Outlined.WaterDrop,
                                                contentDescription = "Humidity icon"
                                            )
                                            Spacer(modifier = Modifier.height(5.dp))
                                            Text(text = "${weatherInfo.main.humidity}")
                                            Spacer(modifier = Modifier.height(5.dp))
                                            Text(
                                                text = "Humidity",
                                                style = MaterialTheme.typography.bodyMedium
                                            )
                                        }
                                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                                            Icon(
                                                imageVector = Icons.Outlined.Thermostat,
                                                contentDescription = "Feels like icon"
                                            )
                                            Spacer(modifier = Modifier.height(5.dp))
                                            Text(text = "${weatherInfo.main.feelsLike?.toInt()} °C")
                                            Spacer(modifier = Modifier.height(5.dp))
                                            Text(
                                                text = "Feels like",
                                                style = MaterialTheme.typography.bodyMedium
                                            )
                                        }
                                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                                            Icon(
                                                imageVector = Icons.Outlined.Air,
                                                contentDescription = "Wind icon"
                                            )
                                            Spacer(modifier = Modifier.height(5.dp))
                                            Text(text = "${weatherInfo.wind.speed?.toInt()} km/h")
                                            Spacer(modifier = Modifier.height(5.dp))
                                            Text(
                                                text = "Wind",
                                                style = MaterialTheme.typography.bodyMedium
                                            )
                                        }
                                    }
                                }
                            }

                            errorMessage != null -> {
                                LaunchedEffect(snackbarHostState) {
                                    snackbarHostState.showSnackbar(errorMessage)
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}