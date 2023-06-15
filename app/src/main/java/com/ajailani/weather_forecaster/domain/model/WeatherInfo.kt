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
    val temp: Float? = null,
    val feelsLike: Float? = null,
    val humidity: Int? = null
)

data class WeatherWind(
    val speed: Float? = null
)
