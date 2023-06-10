package com.ajailani.weather_forecaster.util

import com.ajailani.weather_forecaster.domain.model.Weather
import com.ajailani.weather_forecaster.domain.model.WeatherInfo
import com.ajailani.weather_forecaster.domain.model.WeatherMain
import com.ajailani.weather_forecaster.domain.model.WeatherWind

val weatherInfo = WeatherInfo(
    weather = listOf(
        Weather(
            id = "1",
            main = "Rain",
            description = "Moderate rain"
        )
    ),
    main = WeatherMain(
        temp = 25.0f,
        feelsLike = 28.0f,
        humidity = 20
    ),
    wind = WeatherWind(
        speed = 0.62f
    )
)