package com.ajailani.weather_forecaster.domain.use_case

import com.ajailani.weather_forecaster.domain.model.Location
import com.ajailani.weather_forecaster.domain.repository.WeatherRepository

class SaveLocationUseCase(private val weatherRepository: WeatherRepository) {
    suspend operator fun invoke(location: Location) = weatherRepository.saveLocation(location)
}