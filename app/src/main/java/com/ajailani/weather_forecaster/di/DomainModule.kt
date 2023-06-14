package com.ajailani.weather_forecaster.di

import com.ajailani.weather_forecaster.domain.use_case.GetLocationsUseCase
import com.ajailani.weather_forecaster.domain.use_case.GetWeatherInfoUseCase
import com.ajailani.weather_forecaster.domain.use_case.SyncWeatherInfoUseCase
import org.koin.dsl.module

val domainModule = module {
    single { GetLocationsUseCase(get()) }
    single { GetWeatherInfoUseCase(get()) }
    single { SyncWeatherInfoUseCase(get()) }
}