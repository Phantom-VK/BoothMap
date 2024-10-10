package com.swag.boothmap.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MapStyleOptions
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.MapProperties
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.MarkerState
import com.google.maps.android.compose.rememberCameraPositionState
import com.swag.boothmap.screens.components.SearchBar

@Composable
fun Mapscreen(
    mapStyle: Int,
    paddingValues: PaddingValues,
    locations: List<MapLocation> = emptyList(),
    currentCity: LatLng = LatLng(19.1383, 77.3210),
    cities: List<String>,
    selectedCity: String?,
    onCitySelected: (String) -> Unit
) {
    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(currentCity, 13f)
    }

    LaunchedEffect(currentCity) {
        cameraPositionState.animate(
            update = CameraUpdateFactory.newLatLngZoom(currentCity, 13f),
            durationMs = 1000
        )
    }

    var selectedLocation by remember { mutableStateOf<MapLocation?>(null) }

    Box(modifier = Modifier.fillMaxSize()) {
        GoogleMap(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            cameraPositionState = cameraPositionState,
            properties = MapProperties(
                mapStyleOptions = MapStyleOptions.loadRawResourceStyle(
                    LocalContext.current, mapStyle
                )
            )
        ) {
            locations.forEach { location ->
                Marker(
                    state = MarkerState(position = location.position),
                    title = location.title,
                    snippet = location.snippet,
                    icon = BitmapDescriptorFactory.defaultMarker(
                        hueFromColor(location.color) // You might want to implement hueFromColor() function
                    ),
                    onClick = {
                        selectedLocation = location
                        false
                    }
                )
            }
        }

        selectedLocation?.let { location ->
            Card(
                modifier = Modifier
                    .align(Alignment.BottomStart)
                    .padding(bottom = paddingValues.calculateBottomPadding())
                    .width(260.dp),
                shape = RoundedCornerShape(8.dp),
                colors = CardDefaults.cardColors(
                    containerColor = Color(0xFFFDD692),
                    contentColor = Color(0xFFFFF3ED)
                )
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(location.title, style = MaterialTheme.typography.titleMedium)
                        IconButton(onClick = { selectedLocation = null }) {
                            Icon(Icons.Filled.Close, contentDescription = "Close")
                        }
                    }
                    Text(location.snippet, style = MaterialTheme.typography.bodyMedium)
                    Spacer(modifier = Modifier.height(8.dp))
                    Button(
                        onClick = { /* TODO: Navigate to details screen */ },
                        modifier = Modifier.align(Alignment.End),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color(0xFFFFF3ED),
                            contentColor = Color(0xFFFDD692)
                        )
                    ) {
                        Text("View Details")
                    }
                }
            }
        }

        SearchBar(
            paddingValues = paddingValues,
            cities = cities,
            selectedCity = selectedCity,
            onCitySelected = onCitySelected
        )
    }
}

data class MapLocation(
    val position: LatLng,
    val title: String,
    val snippet: String,
    val color: Color
)

// Helper function to convert Color to hue for BitmapDescriptorFactory
fun hueFromColor(color: Color): Float {
    val hsv = FloatArray(3)
    android.graphics.Color.colorToHSV(color.toArgb(), hsv)
    return hsv[0]
}
