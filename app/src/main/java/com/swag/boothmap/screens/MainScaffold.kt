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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.swag.boothmap.R
import com.swag.boothmap.ui.theme.saffron
import com.swag.boothmap.viewmodels.BoothViewmodel
import com.swag.boothmap.viewmodels.LocationDataViewModel


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScaffoldScreen(
    navController: NavController,
    selectedCity: String,
    locationVM: LocationDataViewModel,
    boothVM: BoothViewmodel
) {
    val mapStyle = R.raw.map_light_style


    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        "BoothMap",
                        color = Color.White,
                        fontStyle = FontStyle.Normal,
                        fontWeight = FontWeight.Medium,
                        fontSize = 25.sp,
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
                    containerColor = saffron,
                    titleContentColor = MaterialTheme.colorScheme.onPrimary,
                    actionIconContentColor = MaterialTheme.colorScheme.onPrimary
                )
            )
        }
    ) { paddingValues ->


            Mapscreen(
                navController = navController,
                mapStyle = mapStyle,
                paddingValues = paddingValues,
                selectedCity = selectedCity,
                locationVM = locationVM,
                boothVM = boothVM

            )

    }
}

