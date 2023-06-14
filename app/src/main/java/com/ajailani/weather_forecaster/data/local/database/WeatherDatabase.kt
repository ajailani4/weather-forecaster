package com.ajailani.weather_forecaster.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.ajailani.weather_forecaster.data.local.dao.WeatherDao
import com.ajailani.weather_forecaster.data.local.entity.WeatherInfoEntity

@Database(
    entities = [WeatherInfoEntity::class],
    version = 1
)
abstract class WeatherDatabase : RoomDatabase() {
    abstract val weatherDao: WeatherDao
}