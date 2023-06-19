package com.ajailani.weather_forecaster.data.local.data_source

import app.cash.sqldelight.coroutines.asFlow
import com.ajailani.weather_forecaster.WeatherForecasterDatabase
import database.WeatherInfoEntity
import kotlinx.coroutines.flow.map

class WeatherLocalDataSource(weatherForecasterDatabase: WeatherForecasterDatabase) {
    private val weatherInfoQueries = weatherForecasterDatabase.weatherInfoQueries

    fun insertWeatherInfo(weatherInfoEntity: WeatherInfoEntity) {
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
        weatherInfoQueries
            .getWeatherInfo()
            .asFlow()
            .map { it.executeAsOneOrNull() }
}
