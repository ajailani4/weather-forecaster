package com.ajailani.weather_forecaster.viewmodel

import com.ajailani.weather_forecaster.domain.use_case.GetLocationNameUseCase
import com.ajailani.weather_forecaster.domain.use_case.GetWeatherInfoUseCase
import com.ajailani.weather_forecaster.domain.use_case.SyncWeatherInfoUseCase
import com.ajailani.weather_forecaster.ui.screen.home.HomeViewModel
import com.ajailani.weather_forecaster.util.Resource
import com.ajailani.weather_forecaster.util.TestCoroutineRule
import com.ajailani.weather_forecaster.util.dummyWeatherInfo
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.ArgumentMatchers.anyString
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.doReturn

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class HomeViewModelTest {
    @get:Rule
    val testCoroutineRule = TestCoroutineRule()

    @Mock
    private lateinit var getLocationNameUseCase: GetLocationNameUseCase

    @Mock
    private lateinit var getWeatherInfoUseCase: GetWeatherInfoUseCase

    @Mock
    private lateinit var syncWeatherInfoUseCase: SyncWeatherInfoUseCase

    private lateinit var homeViewModel: HomeViewModel

    @Test
    fun `Get location name should return a string`() {
        testCoroutineRule.runTest {
            doReturn(flowOf("Bengkulu")).`when`(getLocationNameUseCase)()

            homeViewModel = HomeViewModel(
                getLocationNameUseCase,
                getWeatherInfoUseCase,
                syncWeatherInfoUseCase
            )

            val locationName = homeViewModel.homeUiState.locationName

            assertEquals("Location name should be 'Bengkulu'", "Bengkulu", locationName)
        }
    }

    @Test
    fun `Get weather info should be success`() {
        testCoroutineRule.runTest {
            val resource = flowOf(dummyWeatherInfo)

            doReturn(flowOf("Bengkulu")).`when`(getLocationNameUseCase)()
            doReturn(resource).`when`(getWeatherInfoUseCase)()

            homeViewModel = HomeViewModel(
                getLocationNameUseCase,
                getWeatherInfoUseCase,
                syncWeatherInfoUseCase
            )

            val weatherInfo = homeViewModel.homeUiState.weatherInfo

            assertEquals("Weather should be 'Rain'", "Rain", weatherInfo!!.weathers[0].main)
        }
    }

    @Test
    fun `Sync weather info should be success`() {
        testCoroutineRule.runTest {
            val resource = flowOf(Resource.Success(Any()))

            doReturn(flowOf("Bengkulu")).`when`(getLocationNameUseCase)()
            doReturn(resource).`when`(syncWeatherInfoUseCase)(anyString())

            homeViewModel = HomeViewModel(
                getLocationNameUseCase,
                getWeatherInfoUseCase,
                syncWeatherInfoUseCase
            )

            val errorMessage = homeViewModel.homeUiState.errorMessage

            assertEquals("Error message should be null", null, errorMessage)
        }
    }

    @Test
    fun `Sync weather info should be fail`() {
        val resource = flowOf(Resource.Error<Any>("Error"))

        doReturn(resource).`when`(syncWeatherInfoUseCase)(anyString())

        homeViewModel = HomeViewModel(
            getLocationNameUseCase,
            getWeatherInfoUseCase,
            syncWeatherInfoUseCase
        )

        val errorMessage = homeViewModel.homeUiState.errorMessage

        assertNotNull("Error message shouldn't be null", errorMessage)
    }
}