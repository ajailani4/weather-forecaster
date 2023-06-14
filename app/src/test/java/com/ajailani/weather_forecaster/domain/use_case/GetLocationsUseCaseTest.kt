package com.ajailani.weather_forecaster.domain.use_case

import com.ajailani.weather_forecaster.domain.model.Location
import com.ajailani.weather_forecaster.domain.repository.WeatherRepositoryFake
import com.ajailani.weather_forecaster.util.Resource
import com.ajailani.weather_forecaster.util.ResourceType
import com.ajailani.weather_forecaster.util.dummyLocations
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

@ExperimentalCoroutinesApi
class GetLocationsUseCaseTest {
    private lateinit var weatherRepositoryFake: WeatherRepositoryFake
    private lateinit var getLocationsUseCase: GetLocationsUseCase

    @Before
    fun setUp() {
        weatherRepositoryFake = WeatherRepositoryFake()
        getLocationsUseCase = GetLocationsUseCase(weatherRepositoryFake)
    }

    @Test
    fun `Get locations resource should be success`() =
        runTest(UnconfinedTestDispatcher()) {
            weatherRepositoryFake.setResourceType(ResourceType.Success)

            val actualResource = weatherRepositoryFake.getLocations("Bengkulu").first()

            assertEquals(
                "Resource should be success",
                Resource.Success(dummyLocations),
                actualResource
            )
        }

    @Test
    fun `Get locations resource should be error`() =
        runTest(UnconfinedTestDispatcher()) {
            weatherRepositoryFake.setResourceType(ResourceType.Error)

            val actualResource = weatherRepositoryFake.getLocations("Bengkulu").first()

            assertEquals(
                "Resource should be error",
                Resource.Error<List<Location>>(),
                actualResource
            )
        }
}