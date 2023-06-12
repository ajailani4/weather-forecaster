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
class GetCurrentWeatherUseCaseTest {
    private lateinit var weatherRepositoryFake: WeatherRepositoryFake
    private lateinit var getCurrentWeatherUseCase: GetCurrentWeatherUseCase

    @Before
    fun setUp() {
        weatherRepositoryFake = WeatherRepositoryFake()
        getCurrentWeatherUseCase = GetCurrentWeatherUseCase(weatherRepositoryFake)
    }

    @Test
    fun `Get current weather resource should be success`() =
        runTest(UnconfinedTestDispatcher()) {
            weatherRepositoryFake.setResourceType(ResourceType.Success)

            val actualResource = getCurrentWeatherUseCase(
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