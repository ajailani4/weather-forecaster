package com.ajailani.weather_forecaster.domain.use_case

import com.ajailani.weather_forecaster.domain.repository.WeatherRepository

class SyncCurrentWeatherUseCase(
    private val weatherRepository: WeatherRepository
) {
    operator fun invoke(
        lat: Double,
        lon: Double,
        units: String? = null
    ) = weatherRepository.syncCurrentWeather(
        lat = lat,
        lon = lon,
        units = units
    )
}
