package com.ajailani.weather_forecaster.domain.use_case

import com.ajailani.weather_forecaster.domain.repository.WeatherRepository

class SyncWeatherInfoUseCase(
    private val weatherRepository: WeatherRepository
) {
    operator fun invoke(
        lat: Double,
        lon: Double,
        units: String? = null
    ) = weatherRepository.syncWeatherInfo(
        lat = lat,
        lon = lon,
        units = units
    )
}
