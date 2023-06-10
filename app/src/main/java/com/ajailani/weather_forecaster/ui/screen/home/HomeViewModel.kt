package com.ajailani.weather_forecaster.ui.screen.home

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ajailani.weather_forecaster.domain.use_case.GetCurrentWeatherUseCase
import com.ajailani.weather_forecaster.util.Resource
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class HomeViewModel(
    private val getCurrentWeatherUseCase: GetCurrentWeatherUseCase
) : ViewModel() {
    var homeUiState by mutableStateOf(HomeUiState())
        private set

    init {
        getCurrentWeather()
    }

    private fun getCurrentWeather() {
        homeUiState = homeUiState.copy(loading = true)

        viewModelScope.launch {
            getCurrentWeatherUseCase(
                lat = -6.1753942,
                lon = 106.827183,
                units = "metric"
            ).catch {
                homeUiState = homeUiState.copy(
                    loading = false,
                    errorMessage = it.message
                )
            }.collect {
                homeUiState = when (it) {
                    is Resource.Success -> {
                        homeUiState.copy(
                            loading = false,
                            weatherInfo = it.data
                        )
                    }

                    is Resource.Error -> {
                        homeUiState.copy(
                            loading = false,
                            errorMessage = it.message
                        )
                    }
                }
            }
        }
    }
}