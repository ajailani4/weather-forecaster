package com.ajailani.weather_forecaster.viewmodel

import com.ajailani.weather_forecaster.domain.use_case.GetCurrentWeatherUseCase
import com.ajailani.weather_forecaster.ui.screen.home.HomeViewModel
import com.ajailani.weather_forecaster.util.Resource
import com.ajailani.weather_forecaster.util.TestCoroutineRule
import com.ajailani.weather_forecaster.util.weatherInfo
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.ArgumentMatchers.anyDouble
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
    private lateinit var getCurrentWeatherUseCase: GetCurrentWeatherUseCase

    private lateinit var homeViewModel: HomeViewModel

    @Test
    fun `Get current weather should be success`() {
        testCoroutineRule.runTest {
            val resource = flowOf(Resource.Success(weatherInfo))

            doReturn(resource).`when`(getCurrentWeatherUseCase)(
                lat = anyDouble(),
                lon = anyDouble(),
                units = anyString()
            )

            homeViewModel = HomeViewModel(getCurrentWeatherUseCase)

            val isLoading = homeViewModel.homeUiState.loading
            val weatherInfo = homeViewModel.homeUiState.weatherInfo

            assertEquals("Get current weather shouldn't be loading", false, isLoading)
            assertNotNull("WeatherInfo shouldn't be null", weatherInfo)
            assertEquals("Weather should be 'Rain'", "Rain", weatherInfo!!.weather[0].main)
        }
    }

    @Test
    fun `Get current weather should be fail`() {
        homeViewModel = HomeViewModel(getCurrentWeatherUseCase)

        val isLoading = homeViewModel.homeUiState.loading
        val errorMessage = homeViewModel.homeUiState.errorMessage
        val weatherInfo = homeViewModel.homeUiState.weatherInfo

        assertEquals("Get current weather shouldn't be loading", false, isLoading)
        assertNotNull("Error message shouldn't be null", errorMessage)
        assertEquals("WeatherIno should be null", null, weatherInfo)
    }
}