package com.ajailani.weather_forecaster.data.repository

import com.ajailani.weather_forecaster.data.local.WeatherLocalDataSource
import com.ajailani.weather_forecaster.data.mapper.toWeatherInfo
import com.ajailani.weather_forecaster.data.mapper.toWeatherInfoEntity
import com.ajailani.weather_forecaster.data.remote.data_source.WeatherRemoteDataSource
import com.ajailani.weather_forecaster.data.remote.dto.WeatherInfoDto
import com.ajailani.weather_forecaster.domain.model.Location
import com.ajailani.weather_forecaster.domain.model.WeatherInfo
import com.ajailani.weather_forecaster.domain.repository.WeatherRepository
import com.ajailani.weather_forecaster.util.Resource
import io.ktor.client.call.body
import io.ktor.http.HttpStatusCode
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map

class WeatherRepositoryImpl(
    private val weatherLocalDataSource: WeatherLocalDataSource,
    private val weatherRemoteDataSource: WeatherRemoteDataSource
) : WeatherRepository {
    override fun getWeatherInfo() =
        weatherLocalDataSource.getWeatherInfo().map { it?.toWeatherInfo() }

    override fun syncWeatherInfo(
        lat: Double,
        lon: Double,
        units: String?
    ) =
        flow {
            val response = weatherRemoteDataSource.getCurrentWeather(
                lat = lat,
                lon = lon,
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

    override fun getLocations(query: String): Flow<Resource<List<Location>>> {
        TODO("Not yet implemented")
    }
}