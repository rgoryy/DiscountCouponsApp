package com.grigorii.couponsapp.compose.model

data class MainScreenContentData(
    val offerCoupons: List<CouponApi>?,
    val userCoupons: List<CouponApi>?
)