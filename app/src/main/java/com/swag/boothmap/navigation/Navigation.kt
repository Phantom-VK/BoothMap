package com.swag.boothmap.navigation


import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
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
        composable(route = Screen.MainScaffoldScreen.route+"/{selectedCity}", arguments = listOf(
            navArgument(name = "selectedCity"){
                type = NavType.StringType
            })) {

                it.arguments?.getString("selectedCity")
                    ?.let { it1 -> MainScaffoldScreen(navController, it1) }
        }

        composable(Screen.Splashscreen.route) {
            SplashScreen(navController = navController)
        }

        composable(Screen.CitySelectionScreen.route) {
            CitySelectionScreen(navController = navController)
        }
    }
}
