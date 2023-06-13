package com.ajailani.weather_forecaster.viewmodel

import com.ajailani.weather_forecaster.domain.use_case.SyncCurrentWeatherUseCase
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
    private lateinit var syncCurrentWeatherUseCase: SyncCurrentWeatherUseCase

    private lateinit var homeViewModel: HomeViewModel

    @Test
    fun `Sync current weather should be success`() {
        testCoroutineRule.runTest {
            val resource = flowOf(Resource.Success(Any()))

            doReturn(resource).`when`(syncCurrentWeatherUseCase)(
                lat = anyDouble(),
                lon = anyDouble(),
                units = anyString()
            )

            homeViewModel = HomeViewModel(syncCurrentWeatherUseCase)

            val errorMessage = homeViewModel.homeUiState.errorMessage

            assertEquals("Error message should be null", null, errorMessage)
        }
    }

    @Test
    fun `Sync current weather should be fail`() {
        homeViewModel = HomeViewModel(syncCurrentWeatherUseCase)

        val errorMessage = homeViewModel.homeUiState.errorMessage

        assertNotNull("Error message shouldn't be null", errorMessage)
    }
}