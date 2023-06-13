package com.ajailani.weather_forecaster.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.ajailani.weather_forecaster.data.local.entity.WeatherInfoEntity
import com.ajailani.weather_forecaster.domain.model.WeatherInfo
import kotlinx.coroutines.flow.Flow

@Dao
interface WeatherDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertWeatherInfo(weatherInfoEntity: WeatherInfoEntity)

    @Query("SELECT * FROM WeatherInfoEntity")
    fun getWeatherInfo(): Flow<WeatherInfo>
}