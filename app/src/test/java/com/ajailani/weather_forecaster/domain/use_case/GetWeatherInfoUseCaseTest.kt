package com.ajailani.weather_forecaster.domain.use_case

import com.ajailani.weather_forecaster.domain.repository.WeatherRepositoryFake
import com.ajailani.weather_forecaster.util.ResourceType
import com.ajailani.weather_forecaster.util.dummyWeatherInfo
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

@ExperimentalCoroutinesApi
class GetWeatherInfoUseCaseTest {
    private lateinit var weatherRepositoryFake: WeatherRepositoryFake
    private lateinit var getWeatherInfoUseCase: GetWeatherInfoUseCase

    @Before
    fun setUp() {
        weatherRepositoryFake = WeatherRepositoryFake()
        getWeatherInfoUseCase = GetWeatherInfoUseCase(weatherRepositoryFake)
    }

    @Test
    fun `Get weather info resource should be success`() =
        runTest(UnconfinedTestDispatcher()) {
            weatherRepositoryFake.setResourceType(ResourceType.Success)

            val actualResource = weatherRepositoryFake.getWeatherInfo().first()

            assertEquals(
                "Resource should be success",
                dummyWeatherInfo,
                actualResource
            )
        }
}