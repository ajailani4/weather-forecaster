package com.ajailani.weather_forecaster.di

import com.ajailani.weather_forecaster.data.remote.api_service.WeatherService
import com.ajailani.weather_forecaster.data.remote.data_source.WeatherRemoteDataSource
import com.ajailani.weather_forecaster.data.repository.WeatherRepositoryImpl
import com.ajailani.weather_forecaster.domain.repository.WeatherRepository
import org.koin.dsl.module

val dataModule = module {
    // API Service
    single { WeatherService(get()) }

    // DataSource
    single { WeatherRemoteDataSource(get()) }

    // Repository
    single<WeatherRepository> { WeatherRepositoryImpl(get()) }
}