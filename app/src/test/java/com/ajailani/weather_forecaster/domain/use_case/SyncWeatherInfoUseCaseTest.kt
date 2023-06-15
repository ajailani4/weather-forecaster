package com.ajailani.weather_forecaster.domain.use_case

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
class SyncWeatherInfoUseCaseTest {
    private lateinit var weatherRepositoryFake: WeatherRepositoryFake
    private lateinit var syncWeatherInfoUseCase: SyncWeatherInfoUseCase

    @Before
    fun setUp() {
        weatherRepositoryFake = WeatherRepositoryFake()
        syncWeatherInfoUseCase = SyncWeatherInfoUseCase(weatherRepositoryFake)
    }

    @Test
    fun `Sync weather info resource should be success`() =
        runTest(UnconfinedTestDispatcher()) {
            weatherRepositoryFake.setResourceType(ResourceType.Success)

            val actualResource = syncWeatherInfoUseCase("metric").first()

            assertEquals(
                "Resource should be success",
                Resource.Success<String>(),
                actualResource
            )
        }

    @Test
    fun `Sync weather info resource should be error`() =
        runTest(UnconfinedTestDispatcher()) {
            weatherRepositoryFake.setResourceType(ResourceType.Error)

            val actualResource = syncWeatherInfoUseCase("metric").first()

            assertEquals(
                "Resource should be error",
                Resource.Error<String>(),
                actualResource
            )
        }
}