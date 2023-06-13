package com.ajailani.weather_forecaster.data.repository

import com.ajailani.weather_forecaster.data.local.WeatherLocalDataSource
import com.ajailani.weather_forecaster.data.mapper.toWeatherInfo
import com.ajailani.weather_forecaster.data.remote.data_source.WeatherRemoteDataSource
import com.ajailani.weather_forecaster.data.remote.dto.WeatherInfoDto
import com.ajailani.weather_forecaster.domain.repository.WeatherRepository
import com.ajailani.weather_forecaster.util.Resource
import io.ktor.client.call.body
import io.ktor.http.HttpStatusCode
import kotlinx.coroutines.flow.flow

class WeatherRepositoryImpl(
    private val weatherLocalDataSource: WeatherLocalDataSource,
    private val weatherRemoteDataSource: WeatherRemoteDataSource
) : WeatherRepository {
    override fun syncCurrentWeather(
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
                    val responseBody = response.body<WeatherInfoDto>()
                    emit(Resource.Success(responseBody.toWeatherInfo()))
                }

                HttpStatusCode.InternalServerError -> {
                    emit(Resource.Error("Server error"))
                }

                else -> emit(Resource.Error("Sorry, something wrong happened"))
            }
        }
}