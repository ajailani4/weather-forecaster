package com.ajailani.weather_forecaster.ui.screen.home

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ajailani.weather_forecaster.domain.use_case.GetLocationNameUseCase
import com.ajailani.weather_forecaster.domain.use_case.GetWeatherInfoUseCase
import com.ajailani.weather_forecaster.domain.use_case.SyncWeatherInfoUseCase
import com.ajailani.weather_forecaster.util.Resource
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

class HomeViewModel(
    private val getLocationNameUseCase: GetLocationNameUseCase,
    private val getWeatherInfoUseCase: GetWeatherInfoUseCase,
    private val syncWeatherInfoUseCase: SyncWeatherInfoUseCase
) : ViewModel() {
    var homeUiState by mutableStateOf(HomeUiState())
        private set

    init {
        getLocationName()
        getWeatherInfo()
        syncWeatherInfo()
    }

    private fun getLocationName() {
        viewModelScope.launch {
            getLocationNameUseCase().collect {
                homeUiState = homeUiState.copy(locationName = it)
            }
        }
    }

    fun onEvent(event: HomeEvent) {
        when (event) {
            HomeEvent.SyncWeatherInfo -> syncWeatherInfo()

            is HomeEvent.OnSwipeRefresh -> homeUiState = homeUiState.copy(isRefreshing = true)
        }
    }

    private fun getWeatherInfo() {
        viewModelScope.launch {
            getWeatherInfoUseCase().catch {
                homeUiState = homeUiState.copy(errorMessage = it.message)
            }.collect {
                homeUiState = homeUiState.copy(weatherInfo = it)
            }
        }
    }

    private fun syncWeatherInfo() {
        viewModelScope.launch {
            syncWeatherInfoUseCase("metric").catch {
                homeUiState = homeUiState.copy(
                    isRefreshing = false,
                    errorMessage = it.message
                )
            }.collect {
                homeUiState = when (it) {
                    is Resource.Success -> {
                        homeUiState.copy(
                            isRefreshing = false,
                            errorMessage = null
                        )
                    }

                    is Resource.Error -> {
                        homeUiState.copy(
                            isRefreshing = false,
                            errorMessage = it.message
                        )
                    }
                }
            }
        }
    }
}