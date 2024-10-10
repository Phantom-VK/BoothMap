package com.swag.boothmap.screens.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.calculateEndPadding
import androidx.compose.foundation.layout.calculateStartPadding
import androidx.compose.foundation.layout.fillMaxSize
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchBar(
    paddingValues: PaddingValues,
    cities: List<String>,
    selectedCity: String?,
    onCitySelected: (String) -> Unit
) {
    var expandedCity by remember { mutableStateOf(false) }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                top = paddingValues.calculateTopPadding() + 10.dp,
                start = paddingValues.calculateStartPadding(LayoutDirection.Ltr) + 10.dp,
                end = paddingValues.calculateEndPadding(LayoutDirection.Rtl) + 10.dp
            ),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        ExposedDropdownMenuBox(
            expanded = expandedCity,
            onExpandedChange = { expandedCity = !expandedCity },
            modifier = Modifier
                .weight(1f)
                .height(50.dp)
        ) {
            OutlinedButton(
                onClick = { expandedCity = true },
                colors = ButtonDefaults.buttonColors(Color(0xFFFDD692)),
                border = BorderStroke(1.dp, Color.White),
                shape = RoundedCornerShape(20),
                modifier = Modifier.menuAnchor()
            ) {
                Text(
                    text = selectedCity ?: "Select City",
                    fontStyle = FontStyle.Normal,
                    fontWeight = FontWeight.Medium,
                    fontSize = MaterialTheme.typography.titleLarge.fontSize,
                    color = Color.White
                )
                Icon(
                    modifier = Modifier.size(30.dp),
                    imageVector = Icons.Filled.ArrowDropDown,
                    contentDescription = "City",
                    tint = Color.White
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

        Spacer(Modifier.weight(0.15f))

        OutlinedButton(
            onClick = { /*TODO*/ },
            colors = ButtonDefaults.buttonColors(Color(0xFFFDD692)),
            border = BorderStroke(1.dp, Color.White),
            shape = RoundedCornerShape(20),
            modifier = Modifier
                .height(50.dp)
                .weight(1f)
        ) {
            Text(
                text = "Taluka",
                fontStyle = FontStyle.Normal,
                fontWeight = FontWeight.Medium,
                fontSize = MaterialTheme.typography.titleLarge.fontSize,
                color = Color.White
            )
            Icon(
                modifier = Modifier.size(30.dp),
                imageVector = Icons.Filled.ArrowDropDown,
                contentDescription = "Taluka",
                tint = Color.White
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SearchBarPreview(){
    SearchBar(
        paddingValues = PaddingValues(),
        cities = listOf("Parbhani", "Nanded", "Aurangabad", "Latur"),
        selectedCity = "Parbhani",
        onCitySelected = {})
}