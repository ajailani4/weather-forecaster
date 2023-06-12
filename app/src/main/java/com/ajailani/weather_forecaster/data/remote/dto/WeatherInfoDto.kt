package com.ajailani.weather_forecaster.data.remote.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class WeatherInfoDto(
    @SerialName("weather")
    val weathersDto: List<WeatherDto>,
    @SerialName("main")
    val mainDto: WeatherMainDto,
    @SerialName("wind")
    val windDto: WeatherWindDto
)

@Serializable
data class WeatherDto(
    val id: String,
    val main: String,
    val description: String
)

@Serializable
data class WeatherMainDto(
    val temp: Float,
    val feelsLike: Float,
    val humidity: Int
)

@Serializable
data class WeatherWindDto(
    val speed: Float
)