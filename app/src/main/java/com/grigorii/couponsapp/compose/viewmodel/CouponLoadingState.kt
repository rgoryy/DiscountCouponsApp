package com.grigorii.couponsapp.compose.viewmodel

import com.grigorii.couponsapp.compose.model.Coupon

sealed class CouponLoadingState {
    data object Loading : CouponLoadingState()
    data class Success(val coupons: List<Coupon>) : CouponLoadingState()
    data class Error(val message: String) : CouponLoadingState()
}