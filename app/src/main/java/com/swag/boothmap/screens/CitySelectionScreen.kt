package com.swag.boothmap.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.Text
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.swag.boothmap.R
import com.swag.boothmap.navigation.Screen
import com.swag.boothmap.screens.components.SearchBar
import com.swag.boothmap.viewmodels.LocationDataViewModel

@Composable
fun CitySelectionScreen(
    navController: NavController,
    viewModel: LocationDataViewModel = viewModel()
){
    val cities by viewModel.listOfCities.collectAsState()
    val selectedCity by viewModel.selectedCity.collectAsState()
    Column(
        modifier = Modifier.fillMaxSize()
            .background(Color.White),
        verticalArrangement = Arrangement.SpaceEvenly,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .wrapContentSize()
                .background(Color.White)
        ) {
            Image(
                painter = painterResource(id = R.drawable.eelection_commission_logo),
                contentDescription = "App Logo",

                )
        }
        Text(
            "Select City",
            color = Color.Black,
            fontStyle = FontStyle.Normal,
            fontWeight = FontWeight.Medium,
            fontSize = 30.sp,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )

        SearchBar(
            cities = cities.keys.toList(),
            selectedCity = selectedCity,
            onCitySelected = { city -> viewModel.selectCity(city)},
            talukas = cities.keys.toList(),  //TODO Get Taluka List instead of cities
            selectedTaluka = null,
            onTalukaSelected = {}
        )

        Button(onClick = {
            navController.navigate(Screen.MainScaffoldScreen.route)
        },
            colors = ButtonColors(
                contentColor = Color.White,
                containerColor = Color(0xFF000080),
                disabledContainerColor = Color.Gray,
                disabledContentColor = Color.White

            )
        ){
            Text(text = "Locate Booths",
                color = Color.White)
        }


    }

}


@Preview(showBackground = true)
@Composable
fun CitySelectionScreenPreview() {
    CitySelectionScreen(navController = NavController(LocalContext.current))
}