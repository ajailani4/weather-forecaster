package com.ajailani.weather_forecaster.util

import java.util.Locale

fun String.toCountryName(): String = Locale("", this).displayCountry