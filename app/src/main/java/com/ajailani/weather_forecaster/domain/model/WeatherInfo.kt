package com.ajailani.weather_forecaster.domain.model

data class WeatherInfo(
    val weathers: List<Weather>,
    val main: WeatherMain,
    val wind: WeatherWind
)

data class Weather(
    val id: Int,
    val main: String,
    val description: String
)

data class WeatherMain(
    val temp: Int? = null,
    val feelsLike: Int? = null,
    val humidity: Int? = null
)

data class WeatherWind(
    val speed: Int? = null
)
