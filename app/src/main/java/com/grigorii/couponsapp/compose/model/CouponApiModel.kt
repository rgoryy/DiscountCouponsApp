package com.grigorii.couponsapp.compose.model

data class CouponApiModel(
    val id: Int,
    val title: String,
    val imageUrl: String,
    val location: String,
    val price: String,
    val validityPeriod: String,
    val description: String
)