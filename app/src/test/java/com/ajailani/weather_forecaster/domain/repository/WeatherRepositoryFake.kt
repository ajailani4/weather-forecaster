package com.ajailani.weather_forecaster.domain.repository

import com.ajailani.weather_forecaster.domain.model.Location
import com.ajailani.weather_forecaster.util.Resource
import com.ajailani.weather_forecaster.util.ResourceType
import com.ajailani.weather_forecaster.util.dummyLocations
import com.ajailani.weather_forecaster.util.dummyWeatherInfo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

class WeatherRepositoryFake : WeatherRepository {
    private lateinit var resourceType: ResourceType

    override fun getWeatherInfo() = flowOf(dummyWeatherInfo)

    override fun syncWeatherInfo(units: String?): Flow<Resource<Any>> =
        when (resourceType) {
            ResourceType.Success -> flowOf(Resource.Success())

            ResourceType.Error -> flowOf(Resource.Error())
        }

    override fun getLocations(query: String): Flow<Resource<List<Location>>> =
        when (resourceType) {
            ResourceType.Success -> flowOf(Resource.Success(dummyLocations))

            ResourceType.Error -> flowOf(Resource.Error())
        }

    override suspend fun saveLocation(location: Location) {
        TODO("Not yet implemented")
    }

    override fun getLocationName() = flowOf("Bengkulu")

    fun setResourceType(type: ResourceType) {
        resourceType = type
    }
}