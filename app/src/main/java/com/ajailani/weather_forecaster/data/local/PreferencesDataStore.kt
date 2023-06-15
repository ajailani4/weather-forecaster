package com.ajailani.weather_forecaster.data.local

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.ajailani.weather_forecaster.domain.model.Location
import com.ajailani.weather_forecaster.util.Constants
import kotlinx.coroutines.flow.map

class PreferencesDataStore(private val context: Context) {
    companion object {
        private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(
            Constants.DataStore.PREFERENCES_NAME
        )
        private val LOCATION_NAME = stringPreferencesKey(Constants.DataStore.LOCATION_NAME)

        // Location coordinates will concat lat and lon to a string. Example result: "-6.18, 106.12"
        private val LOCATION_COORDINATES = stringPreferencesKey(Constants.DataStore.LOCATION_COORDINATES)
    }

    suspend fun saveLocation(location: Location) {
        context.dataStore.edit {
            it[LOCATION_NAME] = location.name
            it[LOCATION_COORDINATES] = "${location.lat}, ${location.lon}"
        }
    }

    fun getLocationName() = context.dataStore.data.map { it[LOCATION_NAME] ?: "" }

    fun getLocationCoordinates() = context.dataStore.data.map { it[LOCATION_COORDINATES] ?: "" }
}