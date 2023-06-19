package com.ajailani.weather_forecaster.data.repository

import com.ajailani.weather_forecaster.data.local.PreferencesDataStore
import com.ajailani.weather_forecaster.data.local.data_source.WeatherLocalDataSource
import com.ajailani.weather_forecaster.data.mapper.toLocation
import com.ajailani.weather_forecaster.data.mapper.toWeatherInfo
import com.ajailani.weather_forecaster.data.mapper.toWeatherInfoEntity
import com.ajailani.weather_forecaster.data.remote.data_source.WeatherRemoteDataSource
import com.ajailani.weather_forecaster.data.remote.dto.LocationDto
import com.ajailani.weather_forecaster.data.remote.dto.WeatherInfoDto
import com.ajailani.weather_forecaster.domain.model.Location
import com.ajailani.weather_forecaster.domain.model.WeatherInfo
import com.ajailani.weather_forecaster.domain.repository.WeatherRepository
import com.ajailani.weather_forecaster.util.Resource
import io.ktor.client.call.body
import io.ktor.http.HttpStatusCode
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map

class WeatherRepositoryImpl(
    private val weatherLocalDataSource: WeatherLocalDataSource,
    private val weatherRemoteDataSource: WeatherRemoteDataSource,
    private val preferencesDataStore: PreferencesDataStore
) : WeatherRepository {
    override fun getWeatherInfo() =
        weatherLocalDataSource.getWeatherInfo().map { it?.toWeatherInfo() }

    override fun syncWeatherInfo(units: String?): Flow<Resource<Any>> =
        flow {
            preferencesDataStore.getLocationCoordinates().collect {
                if (it.isNotEmpty()) {
                    val response = weatherRemoteDataSource.getCurrentWeather(
                        lat = it.split(",")[0].toDouble(),
                        lon = it.split(",")[1].toDouble(),
                        units = units
                    )

                    when (response.status) {
                        HttpStatusCode.OK -> {
                            val weatherInfoDto = response.body<WeatherInfoDto>()

                            weatherLocalDataSource.insertWeatherInfo(weatherInfoDto.toWeatherInfoEntity())

                            emit(Resource.Success(Any()))
                        }

                        HttpStatusCode.InternalServerError -> {
                            emit(Resource.Error("Server error"))
                        }

                        else -> emit(Resource.Error("Sorry, something wrong happened"))
                    }
                }
            }
        }

    override fun getLocations(query: String) =
        flow {
            val response = weatherRemoteDataSource.getLocations(query)

            when (response.status) {
                HttpStatusCode.OK -> {
                    val locationsDto = response.body<List<LocationDto>>()
                    emit(Resource.Success(locationsDto.map { it.toLocation() }))
                }

                HttpStatusCode.InternalServerError -> {
                    emit(Resource.Error("Server error"))
                }

                else -> emit(Resource.Error("Sorry, something wrong happened"))
            }
        }

    override suspend fun saveLocation(location: Location) {
        preferencesDataStore.saveLocation(location)
    }

    override fun getLocationName() = preferencesDataStore.getLocationName()
}