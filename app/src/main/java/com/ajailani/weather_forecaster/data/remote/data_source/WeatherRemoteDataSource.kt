package com.ajailani.weather_forecaster.data.remote.data_source

import com.ajailani.weather_forecaster.data.remote.api_service.WeatherApiService

class WeatherRemoteDataSource(private val weatherApiService: WeatherApiService) {
    suspend fun getCurrentWeather(
        lat: Double,
        lon: Double,
        units: String?
    ) = weatherApiService.getCurrentWeather(
        lat = lat,
        lon = lon,
        units = units
    )

    suspend fun getLocations(query: String) = weatherApiService.getLocations(query)
}