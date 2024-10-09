package com.swag.boothmap

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import com.swag.boothmap.screens.Mapscreen
import com.swag.boothmap.ui.theme.BoothMapTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            BoothMapTheme {
                val isDarkTheme = isSystemInDarkTheme()
                val mapStyle = if (isDarkTheme) R.raw.map_dark_style else R.raw.map_light_style
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Mapscreen(mapStyle, innerPadding)
                }
            }
        }
    }
}

