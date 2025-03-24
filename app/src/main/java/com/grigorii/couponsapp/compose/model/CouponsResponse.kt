package com.grigorii.couponsapp.compose.model

data class CouponsResponse(
    val coupons: List<CouponApi>,
    val totalPages: Int,
    val totalElements: Int,
    val currentPage: Int,
    val pageSize: Int
)
