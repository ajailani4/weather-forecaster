package com.ajailani.weather_forecaster.ui.screen.search_location

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ajailani.weather_forecaster.domain.use_case.GetLocationsUseCase
import com.ajailani.weather_forecaster.util.Resource
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class SearchLocationViewModel(
    private val getLocationsUseCase: GetLocationsUseCase
) : ViewModel() {
    var searchLocationUiState by mutableStateOf(SearchLocationUiState())
        private set

    fun onEvent(event: SearchLocationEvent) {
        when (event) {
            SearchLocationEvent.GetLocations -> getLocations()
        }
    }

    private fun getLocations() {
        searchLocationUiState = searchLocationUiState.copy(loading = true)

        viewModelScope.launch {
            getLocationsUseCase(searchLocationUiState.query).catch {
                searchLocationUiState = searchLocationUiState.copy(
                    loading = false,
                    errorMessage = it.message
                )
            }.collect {
                searchLocationUiState = when (it) {
                    is Resource.Success -> {
                        searchLocationUiState.copy(
                            loading = false,
                            locations = it.data ?: emptyList()
                        )
                    }

                    is Resource.Error -> {
                        searchLocationUiState.copy(
                            loading = false,
                            errorMessage = it.message
                        )
                    }
                }
            }
        }
    }
}