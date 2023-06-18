package com.ajailani.weather_forecaster.data.mapper

import com.ajailani.weather_forecaster.data.remote.dto.WeatherInfoDto
import com.ajailani.weather_forecaster.domain.model.Weather
import com.ajailani.weather_forecaster.domain.model.WeatherInfo
import com.ajailani.weather_forecaster.domain.model.WeatherMain
import com.ajailani.weather_forecaster.domain.model.WeatherWind
import database.WeatherInfoEntity

fun WeatherInfoDto.toWeatherInfoEntity() =
    WeatherInfoEntity(
        id = 1,
        main = weathersDto[0].main,
        description = weathersDto[0].description,
        temp = mainDto.temp?.toDouble(),
        feelsLike = mainDto.feelsLike?.toDouble(),
        humidity = mainDto.humidity?.toLong(),
        speed = windDto.speed?.toDouble()
    )

fun WeatherInfoEntity.toWeatherInfo() =
    WeatherInfo(
        weathers = listOf(
            Weather(
                id = id.toInt(),
                main = main,
                description = description
            )
        ),
        main = WeatherMain(
            temp = temp?.toFloat(),
            feelsLike = feelsLike?.toFloat(),
            humidity = humidity?.toInt()
        ),
        wind = WeatherWind(speed?.toFloat())
    )