package com.swag.boothmap.screens

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.calculateStartPadding
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MapStyleOptions
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.MapProperties
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.MarkerState
import com.google.maps.android.compose.rememberCameraPositionState

@Composable
fun Mapscreen(
    mapStyle: Int,
    paddingValues: PaddingValues
) {

    val nanded = LatLng(19.1383, 77.3210) // Latitude and longitude of Nanded city
    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(nanded, 13f)
    }

    val locations = listOf(
        MapLocation(LatLng(19.1383, 77.3210), "Nanded Central Booth", "Main city voting center", Color.Red),
        MapLocation(LatLng(19.1450, 77.3150), "Vazirabad Booth", "Near Vazirabad Police Station", Color.Blue),
        MapLocation(LatLng(19.1320, 77.3280), "Shivaji Nagar Booth", "Community Hall", Color.Green),
        MapLocation(LatLng(19.1410, 77.3320), "Degloor Naka Booth", "Near Bus Stand", Color.Yellow),
        MapLocation(LatLng(19.1350, 77.3100), "Taroda Booth", "Taroda Government School", Color(0xFF8B00FF)), // Violet
        MapLocation(LatLng(19.1480, 77.3250), "MIDC Booth", "MIDC Area Community Center", Color(0xFFFFA500)), // Orange
        MapLocation(LatLng(19.1300, 77.3180), "Asarjan Booth", "Asarjan Library Hall", Color(0xFF00FFFF))  // Cyan
    )

    var selectedLocation by remember { mutableStateOf<MapLocation?>(null) }
    var isSelected by remember { mutableStateOf(false) }

    Box(modifier = Modifier.fillMaxSize()) {
        GoogleMap(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .clip(RoundedCornerShape(16.dp))
                .border(2.dp, MaterialTheme.colorScheme.primary, RoundedCornerShape(16.dp)),
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
                        hueFromColor(location.color)
                    ),
                    onClick = {
                        selectedLocation = location
                        isSelected
                    }
                )
            }
        }

        selectedLocation?.let { location ->
            Card(
                modifier = Modifier
                    .align(Alignment.TopCenter)
                    .padding(top = paddingValues.calculateTopPadding())
                    .width(300.dp),
                elevation = CardDefaults.cardElevation(defaultElevation = 6.dp)
            ) {
                Column(modifier = Modifier.padding(16.dp)) {

                    Row (
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Absolute.SpaceBetween
                    ){
                        Text(location.title, style = MaterialTheme.typography.titleMedium)
                        IconButton(onClick = { selectedLocation = null }) {
                            Icon(Icons.Filled.Close, contentDescription = "Close")
                        }
                    }
                    Text(location.snippet, style = MaterialTheme.typography.bodyMedium)
                    Spacer(modifier = Modifier.height(8.dp))
                    Button(
                        onClick = { /* TODO: Navigate to details screen */ },
                        modifier = Modifier.align(Alignment.End)
                    ) {
                        Text("View Details")
                    }
                }
            }
        }

        FloatingActionButton(
            onClick = { /* TODO: Implement search location*/ },
            modifier = Modifier
                .align(Alignment.TopStart)
                .padding(top = paddingValues.calculateTopPadding(), start = paddingValues.calculateStartPadding(LayoutDirection.Ltr))
        ) {
            Icon(Icons.Filled.Search, contentDescription = "Search Location")
        }


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
