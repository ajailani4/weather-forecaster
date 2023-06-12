package com.ajailani.weather_forecaster.domain.model

data class WeatherInfo(
    val weathers: List<Weather>,
    val main: WeatherMain,
    val wind: WeatherWind
)

data class Weather(
    val id: String,
    val main: String,
    val description: String
)

data class WeatherMain(
    val temp: Float,
    val feelsLike: Float,
    val humidity: Int
)

data class WeatherWind(
    val speed: Float
)
