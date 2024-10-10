package com.swag.boothmap.screens.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchBar(
    cities: List<String>,
    talukas: List<String>,
    selectedCity: String?,
    selectedTaluka: String?,
    onCitySelected: (String) -> Unit,
    onTalukaSelected: (String) -> Unit
) {
    var expandedCity by remember { mutableStateOf(false) }
    var expandedTaluka by remember { mutableStateOf(false) }

    val saffron = Color(0xFFFF9933)
    val white = Color(0xFFFFFFFF)
    val green = Color(0xFF138808)

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .background(white, RoundedCornerShape(8.dp))
            .border(1.dp, green, RoundedCornerShape(8.dp))
    ) {
        // City Dropdown
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
                    contentColor = white
                ),
                border = BorderStroke(1.dp, green),
                shape = RoundedCornerShape(4.dp),
                modifier = Modifier
                    .menuAnchor()
                    .fillMaxWidth()
            ) {
                Text(
                    text = selectedCity ?: "Select City",
                    fontStyle = FontStyle.Normal,
                    fontWeight = FontWeight.Medium,
                    fontSize = MaterialTheme.typography.titleMedium.fontSize,
                    color = white
                )
                Spacer(Modifier.weight(1f))
                Icon(
                    imageVector = Icons.Filled.ArrowDropDown,
                    contentDescription = "City",
                    tint = white
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
                            onCitySelected(city)
                            expandedCity = false
                        }
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(8.dp))

        // Taluka Dropdown
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
                    containerColor = green,
                    contentColor = white
                ),
                border = BorderStroke(1.dp, saffron),
                shape = RoundedCornerShape(4.dp),
                modifier = Modifier
                    .menuAnchor()
                    .fillMaxWidth()
            ) {
                Text(
                    text = selectedTaluka ?: "Select Taluka",
                    fontStyle = FontStyle.Normal,
                    fontWeight = FontWeight.Medium,
                    fontSize = MaterialTheme.typography.titleMedium.fontSize,
                    color = white
                )
                Spacer(Modifier.weight(1f))
                Icon(
                    imageVector = Icons.Filled.ArrowDropDown,
                    contentDescription = "Taluka",
                    tint = white
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
                            onTalukaSelected(taluka)
                            expandedTaluka = false
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