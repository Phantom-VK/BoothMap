package com.swag.boothmap.screens.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.swag.boothmap.ui.theme.green
import com.swag.boothmap.ui.theme.saffron
import com.swag.boothmap.viewmodels.LocationDataViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OptionsDropdown(
    cities: List<String>,
    talukas: List<String>,
    selectedCity: String? = null,
    selectedTaluka: String? = null,
    selectedBooth: String? = null,
    locationVM: LocationDataViewModel
) {
    var expandedCity by remember { mutableStateOf(false) }
    var expandedTaluka by remember { mutableStateOf(false) }
    var expandedBooths by remember { mutableStateOf(false) }

    val eciBlue = Color(0xFF7CB9E8)    // Light blue for dots

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .background(Color.White, RoundedCornerShape(8.dp))
            .border(2.dp, Color.Black, RoundedCornerShape(8.dp))
    ) {
        // City Dropdown (Saffron)
        ExposedDropdownMenuBox(
            expanded = expandedCity,
            onExpandedChange = { expandedCity = !expandedCity },
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        ) {
            OutlinedButton(
                onClick = { expandedCity = true },
                colors = ButtonDefaults.outlinedButtonColors(
                    containerColor = saffron,
                    contentColor = Color.White
                ),
                border = BorderStroke(2.dp, Color.Black),
                shape = RoundedCornerShape(4.dp),
                modifier = Modifier
                    .menuAnchor()
                    .fillMaxWidth()
                    .height(55.dp)
            ) {
                Text(
                    text = selectedCity ?: "Select City",
                    fontStyle = FontStyle.Normal,
                    fontWeight = FontWeight.Bold,
                    fontSize = MaterialTheme.typography.titleMedium.fontSize,
                    color = Color.White
                )
                Spacer(Modifier.weight(1f))

                    Icon(
                        imageVector = Icons.Filled.ArrowDropDown,
                        contentDescription = null,
                        tint = eciBlue,
                        modifier = Modifier.padding(end = 4.dp)
                    )

            }
            ExposedDropdownMenu(
                expanded = expandedCity,
                onDismissRequest = { expandedCity = false }
            ) {
                cities.forEach { city ->
                    DropdownMenuItem(
                        text = { Text(city) },
                        onClick = {
                            locationVM.selectCity(city)
                            locationVM.selectBooth(null)
                            locationVM.selectTaluka(null)
                            expandedCity = false
                        }
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(8.dp))

        // Taluka Dropdown (White)
        ExposedDropdownMenuBox(
            expanded = expandedTaluka,
            onExpandedChange = { expandedTaluka = !expandedTaluka },
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        ) {
            OutlinedButton(
                onClick = { expandedTaluka = true },
                colors = ButtonDefaults.outlinedButtonColors(
                    containerColor = Color.White,
                    contentColor = Color.Black
                ),
                border = BorderStroke(2.dp, Color.Black),
                shape = RoundedCornerShape(4.dp),
                modifier = Modifier
                    .menuAnchor()
                    .fillMaxWidth()
                    .height(55.dp)
            ) {
                Text(
                    text = selectedTaluka ?: "Select Taluka",
                    fontStyle = FontStyle.Normal,
                    fontWeight = FontWeight.Bold,
                    fontSize = MaterialTheme.typography.titleMedium.fontSize,
                    color = Color.Black
                )
                Spacer(Modifier.weight(1f))
                Icon(
                    imageVector = Icons.Filled.ArrowDropDown,
                    contentDescription = null,
                    tint = eciBlue,
                    modifier = Modifier.padding(end = 4.dp)
                )
            }
            ExposedDropdownMenu(
                expanded = expandedTaluka,
                onDismissRequest = { expandedTaluka = false }
            ) {
                talukas.forEach { taluka ->
                    DropdownMenuItem(
                        text = { Text(taluka) },
                        onClick = {
                            locationVM.selectTaluka(taluka)
                            locationVM.selectBooth(null)
                            expandedTaluka = false
                        }
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(8.dp))

        // Booth Dropdown (Green)
        ExposedDropdownMenuBox(
            expanded = expandedBooths,
            onExpandedChange = { expandedBooths = !expandedBooths },
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        ) {
            OutlinedButton(
                onClick = { expandedBooths = true },
                colors = ButtonDefaults.outlinedButtonColors(
                    containerColor = green,
                    contentColor = Color.White
                ),
                border = BorderStroke(2.dp, Color.Black),
                shape = RoundedCornerShape(4.dp),
                modifier = Modifier
                    .menuAnchor()
                    .fillMaxWidth()
                    .height(55.dp)
            ) {
                Text(
                    text = selectedBooth ?: "Select Booth",
                    fontStyle = FontStyle.Normal,
                    fontWeight = FontWeight.Bold,
                    fontSize = MaterialTheme.typography.titleMedium.fontSize,
                    color = Color.White
                )
                Spacer(Modifier.weight(1f))
                Icon(
                    imageVector = Icons.Filled.ArrowDropDown,
                    contentDescription = null,
                    tint = eciBlue,
                    modifier = Modifier.padding(end = 4.dp)
                )
            }
            ExposedDropdownMenu(
                expanded = expandedBooths,
                onDismissRequest = { expandedBooths = false }
            ) {
                locationVM.getListOfBooths().forEach { booth ->
                    DropdownMenuItem(
                        text = { Text(booth) },
                        onClick = {
                            locationVM.selectBooth(booth)
                            expandedBooths = false
                        }
                    )
                }
            }
        }
    }
}

//@Preview(showBackground = true)
//@Composable
//fun SearchBarPreview(){
//    SearchBar(
//        paddingValues = PaddingValues(),
//        cities = listOf("Parbhani", "Nanded", "Aurangabad", "Latur"),
//        selectedCity = "Parbhani",
//        onCitySelected = {})
//}