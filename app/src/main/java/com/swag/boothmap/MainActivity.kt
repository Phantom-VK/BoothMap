package com.swag.boothmap

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import androidx.navigation.compose.rememberNavController
import com.swag.boothmap.navigation.Navigation
import com.swag.boothmap.ui.theme.BoothMapTheme
import com.swag.boothmap.viewmodels.BoothViewmodel
import com.swag.boothmap.viewmodels.LocationDataViewModel

class MainActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        val locationVM = LocationDataViewModel()
        val boothVM = BoothViewmodel()
        setContent {
            BoothMapTheme {

                    Navigation(navController = rememberNavController(),
                        locationVM = locationVM,
                        boothVM = boothVM
                    )

            }
        }
    }
}

