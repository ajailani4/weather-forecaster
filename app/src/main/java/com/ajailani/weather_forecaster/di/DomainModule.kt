package com.ajailani.weather_forecaster.di

import com.ajailani.weather_forecaster.domain.use_case.GetWeatherInfoUseCase
import com.ajailani.weather_forecaster.domain.use_case.SyncCurrentWeatherUseCase
import org.koin.dsl.module

val domainModule = module {
    single { GetWeatherInfoUseCase(get()) }

    single { SyncCurrentWeatherUseCase(get()) }
}