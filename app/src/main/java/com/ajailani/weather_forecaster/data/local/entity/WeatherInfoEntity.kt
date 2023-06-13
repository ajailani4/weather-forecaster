package com.ajailani.weather_forecaster.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class WeatherInfoEntity(
    @PrimaryKey
    val id: Int,
    val main: String,
    val description: String,
    val temp: Float? = null,
    val feelsLike: Float? = null,
    val humidity: Int? = null,
    val speed: Float? = null
)
