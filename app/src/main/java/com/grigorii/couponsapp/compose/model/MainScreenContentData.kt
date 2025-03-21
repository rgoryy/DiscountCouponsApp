package com.grigorii.couponsapp.compose.model

data class MainScreenContentData(
    val offerCoupons: List<Coupon>?,
    val userCoupons: List<Coupon>?
)