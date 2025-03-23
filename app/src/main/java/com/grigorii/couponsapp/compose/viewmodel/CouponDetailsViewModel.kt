package com.grigorii.couponsapp.compose.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.grigorii.couponsapp.compose.domain.CouponContentUseCase
import com.grigorii.couponsapp.compose.model.CouponApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class CouponDetailsViewModel : ViewModel() {

    private val couponContentUseCase: CouponContentUseCase = CouponContentUseCase()

    var couponLoadingState by mutableStateOf<CouponLoadingState1>(
        CouponLoadingState1.Loading
    )
        private set

    fun fetchCoupon(id: Int) {
        viewModelScope.launch {
            try {
                val loadedCoupon = couponContentUseCase.loadCouponById(id)

                withContext(Dispatchers.Default) {
                    if (loadedCoupon == null) {
                        throw Exception()
                    }
                    couponLoadingState = CouponLoadingState1.Success(loadedCoupon)
                }
            } catch (e: Exception) {
                couponLoadingState = CouponLoadingState1.Error(message = "Coupon can not be loaded")
            }
        }
    }
}

sealed class CouponLoadingState1 {
    data object Loading : CouponLoadingState1()
    data class Success(val coupon: CouponApi) : CouponLoadingState1()
    data class Error(val message: String) : CouponLoadingState1()
}