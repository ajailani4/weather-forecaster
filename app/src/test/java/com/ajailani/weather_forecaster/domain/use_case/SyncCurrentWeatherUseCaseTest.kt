package com.ajailani.weather_forecaster.domain.use_case

import com.ajailani.weather_forecaster.domain.model.WeatherInfo
import com.ajailani.weather_forecaster.domain.repository.WeatherRepositoryFake
import com.ajailani.weather_forecaster.util.Resource
import com.ajailani.weather_forecaster.util.ResourceType
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

@ExperimentalCoroutinesApi
class SyncCurrentWeatherUseCaseTest {
    private lateinit var weatherRepositoryFake: WeatherRepositoryFake
    private lateinit var syncCurrentWeatherUseCase: SyncCurrentWeatherUseCase

    @Before
    fun setUp() {
        weatherRepositoryFake = WeatherRepositoryFake()
        syncCurrentWeatherUseCase = SyncCurrentWeatherUseCase(weatherRepositoryFake)
    }

    @Test
    fun `Sync current weather resource should be success`() =
        runTest(UnconfinedTestDispatcher()) {
            weatherRepositoryFake.setResourceType(ResourceType.Success)

            val actualResource = syncCurrentWeatherUseCase(
                lat = -6.17,
                lon = 106.8,
                units = "metric"
            ).first()

            assertEquals(
                "Resource should be success",
                Resource.Success<WeatherInfo>(null),
                actualResource
            )
        }
}