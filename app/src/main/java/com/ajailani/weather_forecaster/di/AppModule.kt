package com.ajailani.weather_forecaster.di

import org.koin.dsl.module

val appModule = module {
    includes(
        networkModule,
        domainModule,
        viewModelModule
    )
}