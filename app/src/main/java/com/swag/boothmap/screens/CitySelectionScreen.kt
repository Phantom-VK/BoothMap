package com.swag.boothmap.screens

import android.app.Activity
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.swag.boothmap.R
import com.swag.boothmap.navigation.Screen
import com.swag.boothmap.screens.components.OptionsDropdown
import com.swag.boothmap.viewmodels.LocationDataViewModel
import com.swag.boothmap.viewmodels.UiState

@Composable
fun CitySelectionScreen(
    navController: NavController,
    viewModel: LocationDataViewModel
) {
    val selectedCity by viewModel.selectedCity.collectAsState()
    val selectedTaluka by viewModel.selectedTaluka.collectAsState()
    val uiState by viewModel.uiState.collectAsState()
    val cities = viewModel.getListOfCities()
    val talukas = viewModel.getListOfTalukas()

    val context = LocalContext.current

    BackHandler {
        // Exit the app when back button is pressed
        (context as? Activity)?.finish()
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        verticalArrangement = Arrangement.SpaceEvenly,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Logo()
        Title()
        OptionsDropdown(cities = cities, talukas = talukas, viewModel = viewModel)
        NavigationButton(
            navController = navController,
            selectedCity = selectedCity,
            selectedTaluka = selectedTaluka,
            isLoading = uiState is UiState.LoadingBooths
        )
    }

    // Error handling
    if (uiState is UiState.Error) {
        ErrorDialog(message = (uiState as UiState.Error).message)
    }
}

@Composable
private fun Logo() {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.wrapContentSize()
    ) {
        Image(
            painter = painterResource(id = R.drawable.eelection_commission_logo),
            contentDescription = "App Logo",
        )
    }
}

@Composable
private fun Title() {
    Text(
        "Select Location",
        color = Color.Black,
        fontStyle = FontStyle.Normal,
        fontWeight = FontWeight.Medium,
        fontSize = 30.sp,
        maxLines = 1,
        overflow = TextOverflow.Ellipsis
    )
}

@Composable
private fun NavigationButton(
    navController: NavController,
    selectedCity: String?,
    selectedTaluka: String?,
    isLoading: Boolean
) {
    Button(
        onClick = {
            navController.navigate(Screen.MainScaffoldScreen.route + "/$selectedCity")
        },
        colors = ButtonDefaults.buttonColors(
            containerColor = Color(0xFF000080),
            contentColor = Color.White,
        ),
        enabled = selectedCity != null && selectedTaluka != null && !isLoading
    ) {
        if (isLoading) {
            CircularProgressIndicator(color = Color.White, modifier = Modifier.size(24.dp))
        } else {
            Text(text = "Locate Booths", color = Color.White)
        }
    }
}

@Composable
private fun ErrorDialog(message: String) {
    AlertDialog(
        onDismissRequest = { },
        title = { Text("Error") },
        text = { Text(message) },
        confirmButton = {
            TextButton(onClick = { }) {
                Text("OK")
            }
        }
    )
}


//@Preview(showBackground = true)
//@Composable
//fun CitySelectionScreenPreview() {
//    CitySelectionScreen(navController = NavController(LocalContext.current))
//}