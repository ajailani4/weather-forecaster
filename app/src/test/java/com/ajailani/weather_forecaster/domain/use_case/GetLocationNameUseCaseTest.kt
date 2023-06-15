package com.ajailani.weather_forecaster.domain.use_case

import com.ajailani.weather_forecaster.domain.repository.WeatherRepositoryFake
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.Assert.*

import org.junit.Before
import org.junit.Test

@ExperimentalCoroutinesApi
class GetLocationNameUseCaseTest {
    private lateinit var weatherRepositoryFake: WeatherRepositoryFake
    private lateinit var getLocationNameUseCase: GetLocationNameUseCase

    @Before
    fun setUp() {
        weatherRepositoryFake = WeatherRepositoryFake()
        getLocationNameUseCase = GetLocationNameUseCase(weatherRepositoryFake)
    }

    @Test
    fun `Get location name should return a string`() =
        runTest(UnconfinedTestDispatcher()) {
            val actualLocationName = weatherRepositoryFake.getLocationName().first()

            assertEquals("Location name should be 'Bengkulu'", "Bengkulu", actualLocationName)
        }
}