package com.ajailani.weather_forecaster.data.local.data_source

import com.ajailani.weather_forecaster.WeatherForecasterDatabase
import com.ajailani.weather_forecaster.data.local.entity.WeatherInfoEntity
import com.ajailani.weather_forecaster.data.mapper.toWeatherInfo
import kotlinx.coroutines.flow.flowOf

class WeatherLocalDataSource(weatherForecasterDatabase: WeatherForecasterDatabase) {
    private val weatherInfoQueries = weatherForecasterDatabase.weatherInfoQueries

    fun insertWeatherInfo(weatherInfoEntity: database.WeatherInfoEntity) {
        weatherInfoQueries.insertWeatherInfo(
            main = weatherInfoEntity.main,
            description = weatherInfoEntity.description,
            temp = weatherInfoEntity.temp,
            feelsLike = weatherInfoEntity.feelsLike,
            humidity = weatherInfoEntity.humidity,
            speed = weatherInfoEntity.speed
        )
    }

    fun getWeatherInfo() =
        flowOf(
            weatherInfoQueries
                .getWeatherInfo()
                .executeAsOneOrNull()
        )
}
