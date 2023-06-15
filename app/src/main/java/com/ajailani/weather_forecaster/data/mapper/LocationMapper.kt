package com.ajailani.weather_forecaster.data.mapper

import com.ajailani.weather_forecaster.data.remote.dto.LocationDto
import com.ajailani.weather_forecaster.domain.model.Location
import java.util.Locale

fun LocationDto.toLocation() =
    Location(
        name = name,
        lat = lat,
        lon = lon,
        country = country,
        state = state
    )