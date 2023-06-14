package com.ajailani.weather_forecaster.domain.use_case

import com.ajailani.weather_forecaster.domain.repository.WeatherRepository

class GetLocationsUseCase(private val weatherRepository: WeatherRepository) {
    operator fun invoke(query: String) = weatherRepository.getLocations(query)
}
