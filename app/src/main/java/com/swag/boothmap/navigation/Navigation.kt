package com.swag.boothmap.navigation


import BoothDetailsScreen
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.swag.boothmap.screens.CitySelectionScreen
import com.swag.boothmap.screens.MainScaffoldScreen
import com.swag.boothmap.screens.SplashScreen
import com.swag.boothmap.viewmodels.BoothViewmodel
import com.swag.boothmap.viewmodels.LocationDataViewModel


@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun Navigation(
    navController: NavHostController,
    locationVM: LocationDataViewModel,
    boothVM: BoothViewmodel
) {
    NavHost(navController = navController, startDestination = Screen.Splashscreen.route) {
        composable(route = Screen.MainScaffoldScreen.route+"/{selectedCity}", arguments = listOf(
            navArgument(name = "selectedCity"){
                type = NavType.StringType
            })) {

                it.arguments?.getString("selectedCity")
                    ?.let { it1 -> MainScaffoldScreen(navController = navController, it1, locationVM = locationVM, boothVM = boothVM) }
        }

        composable(Screen.Splashscreen.route) {
            SplashScreen(navController = navController)
        }

        composable(Screen.CitySelectionScreen.route) {
            CitySelectionScreen(navController = navController, viewModel = locationVM)
        }

        composable(Screen.BoothdetailsScreen.route) {
            BoothDetailsScreen(navController = navController, boothVM)
        }
    }
}
