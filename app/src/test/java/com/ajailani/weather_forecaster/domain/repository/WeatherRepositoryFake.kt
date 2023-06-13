package com.ajailani.weather_forecaster.domain.repository

import com.ajailani.weather_forecaster.domain.model.WeatherInfo
import com.ajailani.weather_forecaster.util.Resource
import com.ajailani.weather_forecaster.util.ResourceType
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

class WeatherRepositoryFake : WeatherRepository {
    private lateinit var resourceType: ResourceType

    override fun getWeatherInfo(): Flow<WeatherInfo> {
        TODO("Not yet implemented")
    }

    override fun syncCurrentWeather(
        lat: Double,
        lon: Double,
        units: String?
    ): Flow<Resource<Any>> =
        when (resourceType) {
            ResourceType.Success -> flowOf(Resource.Success())

            ResourceType.Error -> flowOf(Resource.Error())
        }

    fun setResourceType(type: ResourceType) {
        resourceType = type
    }
}