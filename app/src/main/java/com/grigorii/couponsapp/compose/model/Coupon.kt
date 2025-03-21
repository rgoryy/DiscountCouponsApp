package com.grigorii.couponsapp.compose.model

import androidx.annotation.DrawableRes

data class Coupon(
    val id: Int,
    val title: String,
    @DrawableRes
    val imageResourceId: Int,
    val imageDescription: String,
    val location: String,
    val price: String,
    val validityPeriod: String,
    val description: String? = "Описание недоступно"
)