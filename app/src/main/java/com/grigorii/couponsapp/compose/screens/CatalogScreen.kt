package com.grigorii.couponsapp.compose.screens

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@Composable
fun CatalogScreen(modifier: Modifier = Modifier) {
    Text(
        "CATALOG SCREEN",
        style = TextStyle(color = Color(0xFF21005D)),
        fontSize = 16.sp
    )
}