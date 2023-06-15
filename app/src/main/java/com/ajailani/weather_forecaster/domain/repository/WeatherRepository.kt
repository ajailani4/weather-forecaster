package com.ajailani.weather_forecaster.domain.repository

import com.ajailani.weather_forecaster.domain.model.Location
import com.ajailani.weather_forecaster.domain.model.WeatherInfo
import com.ajailani.weather_forecaster.util.Resource
import kotlinx.coroutines.flow.Flow

interface WeatherRepository {
    fun getWeatherInfo(): Flow<WeatherInfo?>

    fun syncWeatherInfo(units: String?): Flow<Resource<Any>>

    fun getLocations(query: String): Flow<Resource<List<Location>>>

    fun getLocationName(): Flow<String>
}
