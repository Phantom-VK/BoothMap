package com.swag.boothmap.screens.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.calculateEndPadding
import androidx.compose.foundation.layout.calculateStartPadding
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp

@Composable
fun SearchBar(paddingValues: PaddingValues){
    Row(
        modifier = Modifier
            .padding(top = paddingValues.calculateTopPadding()+10.dp, start = paddingValues.calculateStartPadding(
                LayoutDirection.Ltr)+10.dp, end = paddingValues.calculateEndPadding(LayoutDirection.Rtl)+10.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {



        // Filters Button
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
                text = "City",
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

        Spacer(Modifier.weight(0.15f))

        // Rent Button
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
    SearchBar(paddingValues = PaddingValues())
}