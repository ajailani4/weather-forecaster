package com.ajailani.weather_forecaster.data.local.entity

data class WeatherInfoEntity(
    val id: Int = 1,
    val main: String,
    val description: String,
    val temp: Float? = null,
    val feelsLike: Float? = null,
    val humidity: Int? = null,
    val speed: Float? = null
)
