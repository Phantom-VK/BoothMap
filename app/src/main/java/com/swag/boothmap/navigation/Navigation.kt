package com.swag.boothmap.navigation


import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.swag.boothmap.screens.CitySelectionScreen
import com.swag.boothmap.screens.MainScaffoldScreen
import com.swag.boothmap.screens.SplashScreen


@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun Navigation(
    navController: NavHostController,
    paddingValues: PaddingValues
) {
    NavHost(navController = navController, startDestination = Screen.Splashscreen.route) {
        composable(Screen.MainScaffoldScreen.route) {
            MainScaffoldScreen(navController)
        }

        composable(Screen.Splashscreen.route) {
            SplashScreen(navController = navController)
        }

        composable(Screen.CitySelectionScreen.route) {
            CitySelectionScreen(navController = navController)
        }
    }
}
