package com.grigorii.couponsapp.compose.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class Coupon(
    /*@StringRes*/ val title: String,
    @DrawableRes
    val imageResourceId: Int,
    val imageDescription: String,
    val location: String,
    val price: String,
    val validityPeriod: String,
    val description: String? = "Описание недоступно"
)