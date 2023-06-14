package com.ajailani.weather_forecaster.ui.screen.search_location.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun LocationItem() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { }
    ) {
        Column(modifier = Modifier.padding(horizontal = 20.dp, vertical = 12.dp)) {
            Text(
                text = "Bengkulu",
                style = MaterialTheme.typography.titleMedium
            )
            Spacer(modifier = Modifier.height(5.dp))
            Text(
                text = "Bengkulu, Indonesia",
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }
}