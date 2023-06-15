package com.ajailani.weather_forecaster.di

import com.ajailani.weather_forecaster.ui.screen.home.HomeViewModel
import com.ajailani.weather_forecaster.ui.screen.search_location.SearchLocationViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { SearchLocationViewModel(get()) }
    viewModel { HomeViewModel(get(), get(), get()) }
}