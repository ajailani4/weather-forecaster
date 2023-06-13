package com.ajailani.weather_forecaster.ui.screen.home

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ajailani.weather_forecaster.domain.use_case.SyncCurrentWeatherUseCase
import com.ajailani.weather_forecaster.util.Resource
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

class HomeViewModel(
    private val syncCurrentWeatherUseCase: SyncCurrentWeatherUseCase
) : ViewModel() {
    var homeUiState by mutableStateOf(HomeUiState())
        private set

    init {
        syncCurrentWeather()
    }

    /*private fun getCurrentWeather() {
        viewModelScope.launch {
            getCurrentWeatherUseCase().catch {
                homeUiState = homeUiState.copy()
            }.collect {

            }
        }
    }*/

    private fun syncCurrentWeather() {
        viewModelScope.launch {
            syncCurrentWeatherUseCase(
                lat = -6.1753942,
                lon = 106.827183,
                units = "metric"
            ).catch {
                homeUiState = homeUiState.copy(errorMessage = it.message)
            }.collect {
                homeUiState = when (it) {
                    is Resource.Success -> {
                        homeUiState.copy(errorMessage = null)
                    }

                    is Resource.Error -> {
                        homeUiState.copy(errorMessage = it.message)
                    }
                }
            }
        }
    }
}