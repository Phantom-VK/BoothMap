package com.swag.boothmap.screens

import android.app.Activity
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
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
    locationVM: LocationDataViewModel
) {
    val selectedCity by locationVM.selectedCity.collectAsState()
    val selectedTaluka by locationVM.selectedTaluka.collectAsState()
    val selectedBooth by locationVM.selectedBooth.collectAsState()
    val uiState by locationVM.uiState.collectAsState()
    val cities = locationVM.getListOfCities()
    val talukas = locationVM.getListOfTalukas()

    val context = LocalContext.current

    BackHandler {
        // Exit the app when back button is pressed
        (context as? Activity)?.finish()
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(Modifier.height(30.dp))
        Logo()

        Spacer(Modifier.height(40.dp))
        Title()
        OptionsDropdown(
            cities = cities,
            talukas = talukas,
            locationVM = locationVM,
            selectedCity = selectedCity,
            selectedBooth = selectedBooth,
            selectedTaluka = selectedTaluka
        )
        Spacer(Modifier.height(20.dp))
        NavigationButton(
            onClick = {
                navController.navigate(Screen.MainScaffoldScreen.route + "/$selectedCity/$selectedBooth")


            },
            selectedCity = selectedCity,
            selectedTaluka = selectedTaluka,
            selectedBooth = selectedBooth,
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
    onClick: () -> Unit,
    selectedCity: String?,
    selectedTaluka: String?,
    selectedBooth: String?,
    isLoading: Boolean
) {
    Button(
        modifier = Modifier.fillMaxWidth()
            .padding(16.dp)
            .height(50.dp),
        onClick = {
            onClick()

        },
        colors = ButtonDefaults.buttonColors(
            containerColor = Color(0xFF000080),
            contentColor = Color.White,
        ),
        enabled = selectedCity != null && selectedTaluka != null && !isLoading && selectedBooth != null
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