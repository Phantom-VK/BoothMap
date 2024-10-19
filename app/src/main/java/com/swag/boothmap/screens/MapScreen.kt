package com.swag.boothmap.screens

import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
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
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
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
import com.swag.boothmap.datacalsses.Booth
import com.swag.boothmap.navigation.Screen
import com.swag.boothmap.ui.theme.green
import com.swag.boothmap.ui.theme.saffron
import com.swag.boothmap.ui.theme.white
import com.swag.boothmap.viewmodels.BoothViewmodel
import com.swag.boothmap.viewmodels.LocationDataViewModel

@Composable
fun Mapscreen(
    navController: NavController,
    mapStyle: Int,
    paddingValues: PaddingValues,
    selectedCity: String,
    selectedBooth: String, // selectedBooth from the booth list
    locationVM: LocationDataViewModel,
    boothVM: BoothViewmodel
) {
    val booths by locationVM.booths.collectAsState()
    var selectedBoothData by remember { mutableStateOf<Booth?>(null) } // Selected booth data
    val context = LocalContext.current

    // Default location
    val defaultLocation = LatLng(19.1383, 77.3210) // Default coordinates for Maharashtra

    // Find the selected booth based on the selectedBooth ID
    LaunchedEffect(selectedBooth) {
        selectedBoothData = booths.find { it.name == selectedBooth } // Assuming boothId is unique
    }

    val initialLocation = selectedBoothData?.let {
        LatLng(it.latitude, it.longitude)
    } ?: defaultLocation

    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(initialLocation, 13f)
    }

    LaunchedEffect(selectedBoothData) {
        selectedBoothData?.let { booth ->
            cameraPositionState.animate(
                update = CameraUpdateFactory.newLatLngZoom(
                    LatLng(booth.latitude, booth.longitude), 13f
                ),
                durationMs = 1000
            )
        }
    }

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
            selectedBoothData?.let { booth ->
                Marker(
                    state = MarkerState(position = LatLng(booth.latitude, booth.longitude)),
                    title = booth.name,
                    snippet = booth.taluka,
                    icon = BitmapDescriptorFactory.defaultMarker(),
                    onClick = {
                        false
                    }
                )
            }
        }

        selectedBoothData?.let { booth ->
            Card(
                modifier = Modifier
                    .align(Alignment.BottomStart)
                    .padding(16.dp)
                    .fillMaxWidth()
                    .padding(bottom = paddingValues.calculateBottomPadding()),
                shape = RoundedCornerShape(8.dp),
                colors = CardDefaults.cardColors(
                    containerColor = saffron,
                    contentColor = Color(0xFFFFF3ED)
                )
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(booth.name, style = MaterialTheme.typography.titleMedium)
                        IconButton(onClick = { selectedBoothData = null }) {
                            Icon(Icons.Filled.Close, contentDescription = "Close")
                        }
                    }
                    Text(booth.taluka, style = MaterialTheme.typography.bodyMedium)
                    Text("BLO: ${booth.bloName}", style = MaterialTheme.typography.bodySmall)
                    Text("Contact: ${booth.bloContact}", style = MaterialTheme.typography.bodySmall)
                    Spacer(modifier = Modifier.height(8.dp))
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Button(
                            onClick = {
                                boothVM.selectedBooth = booth
                                navController.navigate(Screen.BoothdetailsScreen.route)
                            },
                            colors = ButtonDefaults.buttonColors(
                                containerColor = green,
                                contentColor = white
                            )
                        ) {
                            Text("View Details")
                        }

                        Button(
                            onClick = {
                                // Open Google Maps with directions
                                val uri = Uri.parse(
                                    "google.navigation:q=${booth.latitude},${booth.longitude}"
                                )
                                val mapIntent = Intent(Intent.ACTION_VIEW, uri)
                                try {
                                    context.startActivity(mapIntent)
                                } catch (e: ActivityNotFoundException) {
                                    // If Google Maps is not installed, open in browser
                                    val browserUri = Uri.parse(
                                        "https://www.google.com/maps/dir/?api=1&destination=${booth.latitude},${booth.longitude}"
                                    )
                                    val browserIntent = Intent(Intent.ACTION_VIEW, browserUri)
                                    context.startActivity(browserIntent)
                                }
                            },
                            colors = ButtonDefaults.buttonColors(
                                containerColor = MaterialTheme.colorScheme.primary,
                                contentColor = white
                            )
                        ) {
                            Text("Get Directions")
                        }
                    }
                }
            }
        }
    }
}



