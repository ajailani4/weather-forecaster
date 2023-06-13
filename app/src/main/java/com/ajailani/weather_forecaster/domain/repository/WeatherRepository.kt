package com.ajailani.weather_forecaster.domain.repository

import com.ajailani.weather_forecaster.domain.model.WeatherInfo
import com.ajailani.weather_forecaster.util.Resource
import kotlinx.coroutines.flow.Flow

interface WeatherRepository {
    fun getWeatherInfo(): Flow<WeatherInfo>

    fun syncCurrentWeather(
        lat: Double,
        lon: Double,
        units: String?
    ): Flow<Resource<Any>>
}
