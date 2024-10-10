package com.swag.boothmap.screens

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.google.android.gms.maps.model.LatLng
import com.swag.boothmap.R
import com.swag.boothmap.viewmodels.LocationDataViewModel


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScaffoldScreen(
    navHostController: NavHostController,
    viewModel: LocationDataViewModel = viewModel()
) {
    val mapStyle = R.raw.map_light_style


    val locations by viewModel.locations.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        "BoothMap",
                        color = Color.White,
                        fontStyle = FontStyle.Normal,
                        fontWeight = FontWeight.Medium,
                        fontSize = 30.sp,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                },
                actions = {
                    IconButton(onClick = {
                        //TODO() Navigate to settingscreen
                    }) {
                        Icon(Icons.Filled.Settings,
                            contentDescription = "Settings",
                            tint = Color.White)
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color(0xFFFDD692),
                    titleContentColor = MaterialTheme.colorScheme.onPrimary,
                    actionIconContentColor = MaterialTheme.colorScheme.onPrimary
                )
            )
        }
    ) { paddingValues ->


            Mapscreen(
                mapStyle = mapStyle,
                paddingValues = paddingValues,
                locations = locations,
                currentCity = locations.firstOrNull()?.position ?: LatLng(19.1383, 77.3210)
            )

    }
}

