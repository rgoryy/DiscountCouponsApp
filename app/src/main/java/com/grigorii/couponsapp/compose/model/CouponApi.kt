package com.grigorii.couponsapp.compose.model

data class CouponApi(
    val id: Int,
    val title: String,

    val imageUrl: String,

    val imageDescription: String = "",
    val location: String,
    val price: String,
    val validityPeriod: String,
    val description: String = "Описание недоступно"
)