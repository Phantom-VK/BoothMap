package com.swag.boothmap.screens

import android.view.animation.OvershootInterpolator
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.swag.boothmap.R
import com.swag.boothmap.navigation.Screen
import com.swag.boothmap.viewmodels.LocationDataViewModel
import com.swag.boothmap.viewmodels.UiState

@Composable
fun SplashScreen(
    navController: NavController,
    viewModel: LocationDataViewModel = viewModel()
) {
    val scale = remember { Animatable(2f) }
    val uiState by viewModel.uiState.collectAsState()

    LaunchedEffect(key1 = true) {
        scale.animateTo(
            targetValue = 0.7f,
            animationSpec = tween(
                durationMillis = 800,
                easing = {
                    OvershootInterpolator(4f).getInterpolation(it)
                }
            )
        )
    }

    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        Image(
            painter = painterResource(id = R.drawable.eelection_commission_logo),
            contentDescription = "App Logo",
            modifier = Modifier.scale(scale.value)
        )

        when (uiState) {
            is UiState.LoadingCities -> {
                CircularProgressIndicator(
                    modifier = Modifier
                        .align(Alignment.BottomCenter)
                        .padding(bottom = 32.dp)
                )
            }
            is UiState.CitiesLoaded -> {
                LaunchedEffect(key1 = true) {
                    navController.navigate(Screen.CitySelectionScreen.route)
                }
            }
            is UiState.Error -> {
                Text(
                    text = (uiState as UiState.Error).message,
                    color = Color.Red,
                    modifier = Modifier
                        .align(Alignment.BottomCenter)
                        .padding(bottom = 32.dp)
                )
            }
            else -> {
                // Do nothing for other states (LoadingBooths, BoothsLoaded)
                // as they shouldn't occur during the splash screen
            }
        }
    }
}