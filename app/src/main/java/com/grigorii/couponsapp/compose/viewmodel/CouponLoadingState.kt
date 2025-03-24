package com.grigorii.couponsapp.compose.viewmodel

import com.grigorii.couponsapp.compose.model.CouponApi

sealed class CouponLoadingState {
    data object Loading : CouponLoadingState()
    data class Success(val coupons: List<CouponApi>) : CouponLoadingState()
    data class Error(val message: String) : CouponLoadingState()
}