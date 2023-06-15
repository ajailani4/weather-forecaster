package com.ajailani.weather_forecaster.viewmodel

import android.location.Location
import com.ajailani.weather_forecaster.domain.use_case.GetLocationsUseCase
import com.ajailani.weather_forecaster.domain.use_case.SaveLocationUseCase
import com.ajailani.weather_forecaster.ui.screen.search_location.SearchLocationEvent
import com.ajailani.weather_forecaster.ui.screen.search_location.SearchLocationViewModel
import com.ajailani.weather_forecaster.util.Resource
import com.ajailani.weather_forecaster.util.TestCoroutineRule
import com.ajailani.weather_forecaster.util.dummyLocations
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.ArgumentMatchers.anyString
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.doReturn

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class SearchLocationViewModelTest {
    @get:Rule
    val testCoroutineRule = TestCoroutineRule()

    @Mock
    private lateinit var getLocationsUseCase: GetLocationsUseCase

    @Mock
    private lateinit var saveLocationUseCase: SaveLocationUseCase

    private lateinit var searchLocationViewModel: SearchLocationViewModel

    private lateinit var onEvent: (SearchLocationEvent) -> Unit

    @Before
    fun setUp() {
        searchLocationViewModel = SearchLocationViewModel(getLocationsUseCase, saveLocationUseCase)
        onEvent = searchLocationViewModel::onEvent
    }

    @Test
    fun `Get locations should be success`() {
        testCoroutineRule.runTest {
            val resource = flowOf(Resource.Success(dummyLocations))

            doReturn(resource).`when`(getLocationsUseCase)(anyString())

            onEvent(SearchLocationEvent.GetLocations)

            val locations = searchLocationViewModel.searchLocationUiState.locations
            val errorMessage = searchLocationViewModel.searchLocationUiState.errorMessage

            assertEquals("Locations size should be 2", 2, locations.size)
            assertEquals("Error message should be null", null, errorMessage)
        }
    }

    @Test
    fun `Get locations should be fail`() {
        testCoroutineRule.runTest {
            val resource = flowOf(Resource.Error<List<Location>>("Error"))

            doReturn(resource).`when`(getLocationsUseCase)(anyString())

            onEvent(SearchLocationEvent.GetLocations)

            val locations = searchLocationViewModel.searchLocationUiState.locations
            val errorMessage = searchLocationViewModel.searchLocationUiState.errorMessage

            assertEquals("Locations should be empty", true, locations.isEmpty())
            assertNotNull("Error message shouldn't be null", errorMessage)
        }
    }
}