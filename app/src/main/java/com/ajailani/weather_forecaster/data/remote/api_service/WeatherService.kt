package com.ajailani.weather_forecaster.data.remote.api_service

import io.ktor.client.HttpClient
import io.ktor.client.request.get

class WeatherService(private val httpClient: HttpClient) {
    suspend fun getCurrentWeather(
        lat: Double,
        lon: Double,
        units: String?
    ) = httpClient.get("data/2.5/weather") {
        url {
            parameters.append("lat", lat.toString())
            parameters.append("lon", lon.toString())

            units?.let {
                parameters.append("units", it)
            }
        }
    }
}