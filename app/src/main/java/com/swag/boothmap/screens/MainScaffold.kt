package com.swag.boothmap.screens

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.text.style.TextOverflow
import com.swag.boothmap.R

sealed class Screen(val route: String) {
    object Mapscreen : Screen("Map")
    object Dashboard : Screen("Dashboard")
    object Billingscreen : Screen("Billing")
    object Inventoryscreen : Screen("Inventory")
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScaffoldScreen() {
    val isDarkTheme = isSystemInDarkTheme()
    val mapStyle = if (isDarkTheme) R.raw.map_dark_style else R.raw.map_light_style

    var currentScreen by remember { mutableStateOf<Screen>(Screen.Mapscreen) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("BoothMap", maxLines = 1, overflow = TextOverflow.Ellipsis) },
                actions = {
                    IconButton(onClick = {
                        //TODO() Navigate to settingscreen
                    }) {
                        Icon(Icons.Filled.Settings, contentDescription = "Settings")
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    titleContentColor = MaterialTheme.colorScheme.onPrimary,
                    actionIconContentColor = MaterialTheme.colorScheme.onPrimary
                )
            )
        },
        bottomBar = {
//            NavigationBar(
//                containerColor = MaterialTheme.colorScheme.primaryContainer,
//                contentColor = MaterialTheme.colorScheme.onPrimaryContainer
//            ) {
//                listOf(
//                    Screen.Mapscreen to Icons.Filled.LocationOn
//
//                ).forEach { (screen, icon) ->
//                    NavigationBarItem(
//                        icon = {
//                             Icon(icon, contentDescription = screen.route)
//
//                        },
//                        label = { Text(screen.route) },
//                        selected = currentScreen == screen,
//                        onClick = { currentScreen = screen },
//                        colors = NavigationBarItemDefaults.colors(
//                            selectedIconColor = MaterialTheme.colorScheme.onPrimaryContainer,
//                            unselectedIconColor = MaterialTheme.colorScheme.onPrimaryContainer.copy(alpha = 0.6f),
//                            selectedTextColor = MaterialTheme.colorScheme.onPrimaryContainer,
//                            unselectedTextColor = MaterialTheme.colorScheme.onPrimaryContainer.copy(alpha = 0.6f),
//                            indicatorColor = MaterialTheme.colorScheme.primaryContainer
//                        )
//                    )
//                }
//            }
        }
    ) { paddingValues ->

        Mapscreen(mapStyle, paddingValues)
//        when (currentScreen) {
//            Screen.Mapscreen ->
//
//        }
    }
}

