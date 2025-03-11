package com.grigorii.couponsapp.compose.screens

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.sp

@Composable
fun LoginScreen(modifier: Modifier = Modifier) {
    Text(
        "LOGIN SCREEN",
        style = TextStyle(color = Color(0xFF21005D)),
        fontSize = 16.sp
    )
}