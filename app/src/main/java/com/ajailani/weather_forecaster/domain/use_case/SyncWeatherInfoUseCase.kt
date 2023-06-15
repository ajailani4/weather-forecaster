package com.ajailani.weather_forecaster.domain.use_case

import com.ajailani.weather_forecaster.domain.repository.WeatherRepository

class SyncWeatherInfoUseCase(
    private val weatherRepository: WeatherRepository
) {
    operator fun invoke(units: String? = null) = weatherRepository.syncWeatherInfo(units = units)
}
