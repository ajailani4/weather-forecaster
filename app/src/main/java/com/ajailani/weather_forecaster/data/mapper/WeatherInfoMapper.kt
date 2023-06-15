package com.ajailani.weather_forecaster.data.mapper

import com.ajailani.weather_forecaster.data.local.entity.WeatherInfoEntity
import com.ajailani.weather_forecaster.data.remote.dto.WeatherDto
import com.ajailani.weather_forecaster.data.remote.dto.WeatherInfoDto
import com.ajailani.weather_forecaster.data.remote.dto.WeatherMainDto
import com.ajailani.weather_forecaster.data.remote.dto.WeatherWindDto
import com.ajailani.weather_forecaster.domain.model.Weather
import com.ajailani.weather_forecaster.domain.model.WeatherInfo
import com.ajailani.weather_forecaster.domain.model.WeatherMain
import com.ajailani.weather_forecaster.domain.model.WeatherWind

fun WeatherInfoDto.toWeatherInfoEntity() =
    WeatherInfoEntity(
        main = weathersDto[0].main,
        description = weathersDto[0].description,
        temp = mainDto.temp,
        feelsLike = mainDto.feelsLike,
        humidity = mainDto.humidity,
        speed = windDto.speed
    )

fun WeatherInfoEntity.toWeatherInfo() =
    WeatherInfo(
        weathers = listOf(
            Weather(
                id = id,
                main = main,
                description = description
            )
        ),
        main = WeatherMain(
            temp = temp,
            feelsLike = feelsLike,
            humidity = humidity
        ),
        wind = WeatherWind(speed)
    )