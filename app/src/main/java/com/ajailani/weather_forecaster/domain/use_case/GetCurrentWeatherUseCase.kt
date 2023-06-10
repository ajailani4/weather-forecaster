package com.ajailani.weather_forecaster.domain.use_case

import com.ajailani.weather_forecaster.domain.repository.WeatherRepository

class GetCurrentWeatherUseCase(
    private val weatherRepository: WeatherRepository
) {
    operator fun invoke(
        lat: Double,
        lon: Double,
        units: String? = null
    ) = weatherRepository.getCurrentWeather(
        lat = lat,
        lon = lon,
        units = units
    )
}
