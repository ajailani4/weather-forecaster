package com.ajailani.weather_forecaster.data.local

import com.ajailani.weather_forecaster.data.local.dao.WeatherDao
import com.ajailani.weather_forecaster.data.local.entity.WeatherInfoEntity

class WeatherLocalDataSource(private val weatherDao: WeatherDao) {
    suspend fun insertWeatherInfo(weatherInfoEntity: WeatherInfoEntity) =
        weatherDao.insertWeatherInfo(weatherInfoEntity)

    fun getWeatherInfo() = weatherDao.getWeatherInfo()
}