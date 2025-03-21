package com.grigorii.couponsapp.compose.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.grigorii.couponsapp.compose.domain.CouponContentUseCase
import com.grigorii.couponsapp.compose.model.Coupon
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainScreenViewModel : ViewModel() {

    private val couponContentUseCase = CouponContentUseCase()

    var offerCouponsState by mutableStateOf<MainScreenCouponLoadingState>(MainScreenCouponLoadingState.Loading)
        private set

    var usersCouponsState by mutableStateOf<MainScreenCouponLoadingState>(MainScreenCouponLoadingState.Loading)
        private set

    fun fetchContent() {
        viewModelScope.launch {
            try {
                val userCoupons = couponContentUseCase.loadUsersCoupons()
                withContext(Dispatchers.Default) {
                    usersCouponsState = MainScreenCouponLoadingState.Success(userCoupons)
                }
            } catch (e: Exception) {
                usersCouponsState = MainScreenCouponLoadingState.Error("Ошибка загрузки купонов пользователя")
            }
        }

        viewModelScope.launch {
            try {
                val offerCoupons = couponContentUseCase.loadOfferCoupons()
                withContext(Dispatchers.Default) {
                    offerCouponsState = MainScreenCouponLoadingState.Success(offerCoupons)
                }
            } catch (e: Exception) {
                offerCouponsState = MainScreenCouponLoadingState.Error("Ошибка загрузки предложений")
            }
        }
    }
}

sealed class MainScreenCouponLoadingState {
    data object Loading : MainScreenCouponLoadingState()
    data class Success(val coupons: List<Coupon>) : MainScreenCouponLoadingState()
    data class Error(val message: String) : MainScreenCouponLoadingState()
}