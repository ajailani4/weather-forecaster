package com.ajailani.weather_forecaster.ui.screen.search_location

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.ajailani.weather_forecaster.ui.screen.search_location.component.LocationItem
import com.ajailani.weather_forecaster.ui.screen.search_location.component.SearchTextField

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchLocationScreen(
    onEvent: (SearchLocationEvent) -> Unit,
    searchLocationUiState: SearchLocationUiState,
    onNavigateToHome: () -> Unit
) {
    val snackbarHostState = remember { SnackbarHostState() }

    Scaffold(
        snackbarHost = { SnackbarHost(hostState = snackbarHostState) },
        topBar = {
            Row(
                modifier = Modifier.padding(horizontal = 12.dp, vertical = 15.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(
                    modifier = Modifier.size(24.dp),
                    onClick = { /*TODO*/ }
                ) {
                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = "Back icon"
                    )
                }
                Spacer(modifier = Modifier.width(13.dp))
                SearchTextField(
                    value = searchLocationUiState.query,
                    onValueChange = { onEvent(SearchLocationEvent.OnQueryChanged(it)) },
                    onClearText = { onEvent(SearchLocationEvent.OnQueryChanged("")) },
                    onSearch = { onEvent(SearchLocationEvent.GetLocations) }
                )
            }
        }
    ) { innerPadding ->
        searchLocationUiState.apply {
            when {
                !loading && errorMessage == null -> {
                    LazyColumn(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(innerPadding)
                    ) {
                        items(locations) {
                            LocationItem(
                                location = it,
                                onClick = {
                                    onEvent(SearchLocationEvent.SaveLocation(it))
                                    onNavigateToHome()
                                }
                            )
                        }
                    }
                }

                loading -> {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        CircularProgressIndicator()
                    }
                }

                errorMessage != null -> {
                    LaunchedEffect(snackbarHostState) {
                        snackbarHostState.showSnackbar(errorMessage)
                    }
                }
            }
        }
    }
}