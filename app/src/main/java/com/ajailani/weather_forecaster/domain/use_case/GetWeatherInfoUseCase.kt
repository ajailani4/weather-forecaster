package com.ajailani.weather_forecaster.domain.use_case

import com.ajailani.weather_forecaster.domain.repository.WeatherRepository

class GetWeatherInfoUseCase(private val weatherRepository: WeatherRepository) {
    operator fun invoke() = weatherRepository.getWeatherInfo()

}
