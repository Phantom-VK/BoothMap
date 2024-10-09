package com.swag.boothmap

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.swag.boothmap.screens.MainScaffoldScreen
import com.swag.boothmap.ui.theme.BoothMapTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            BoothMapTheme {

                    MainScaffoldScreen()

            }
        }
    }
}

