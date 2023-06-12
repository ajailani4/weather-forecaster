package com.ajailani.weather_forecaster.data.mapper

import com.ajailani.weather_forecaster.data.remote.dto.WeatherDto
import com.ajailani.weather_forecaster.data.remote.dto.WeatherInfoDto
import com.ajailani.weather_forecaster.data.remote.dto.WeatherMainDto
import com.ajailani.weather_forecaster.data.remote.dto.WeatherWindDto
import com.ajailani.weather_forecaster.domain.model.Weather
import com.ajailani.weather_forecaster.domain.model.WeatherInfo
import com.ajailani.weather_forecaster.domain.model.WeatherMain
import com.ajailani.weather_forecaster.domain.model.WeatherWind

fun WeatherInfoDto.toWeatherInfo() =
    WeatherInfo(
        weathers = weathersDto.map { it.toWeather() },
        main = mainDto.toWeatherMain(),
        wind = windDto.toWeatherWind()
    )

fun WeatherDto.toWeather() =
    Weather(
        id = id,
        main = main,
        description = description
    )

fun WeatherMainDto.toWeatherMain() =
    WeatherMain(
        temp = temp?.toInt(),
        feelsLike = feelsLike?.toInt(),
        humidity = humidity
    )

fun WeatherWindDto.toWeatherWind() =
    WeatherWind(
        speed = speed?.toInt()
    )