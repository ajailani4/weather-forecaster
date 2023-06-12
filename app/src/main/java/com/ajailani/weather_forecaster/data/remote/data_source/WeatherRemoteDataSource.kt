package com.ajailani.weather_forecaster.data.remote.data_source

import com.ajailani.weather_forecaster.data.remote.api_service.WeatherService

class WeatherRemoteDataSource(private val weatherService: WeatherService) {
    suspend fun getCurrentWeather(
        lat: Double,
        lon: Double,
        units: String?
    ) = weatherService.getCurrentWeather(
        lat = lat,
        lon = lon,
        units = units
    )
}