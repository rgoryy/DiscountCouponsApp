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

    var offerCouponsState by mutableStateOf<MainScreenCouponLoadingState>(MainScreenCouponLoadingState.Loading)
        private set

    var usersCouponsState by mutableStateOf<MainScreenCouponLoadingState>(MainScreenCouponLoadingState.Loading)
        private set

    private val couponContentUseCase = CouponContentUseCase()

    private var offerCouponsPage = 1
    private var userCouponsPage = 1
    private val userPageSize = 3
    private val offersPageSize = 2

    private val currentLoadedOfferCoupons = mutableListOf<Coupon>()
    private val currentLoadedUserCoupons  = mutableListOf<Coupon>()

    fun fetchContent() {
        fetchUserCoupons()
        fetchOfferCoupons()
    }

    fun fetchUserCoupons() {
        viewModelScope.launch {
            try {
                val userCoupons = couponContentUseCase.loadUserCoupons(userCouponsPage, userPageSize)
                userCouponsPage++

                val updatedUserCoupons = currentLoadedUserCoupons + userCoupons
                currentLoadedUserCoupons.addAll(userCoupons)

                withContext(Dispatchers.Default) {
                    usersCouponsState = MainScreenCouponLoadingState.Success(updatedUserCoupons)
                }
            } catch (e: Exception) {
                usersCouponsState = MainScreenCouponLoadingState.Error("Ошибка загрузки купонов пользователя")
            }
        }
    }

    fun fetchOfferCoupons() {
        viewModelScope.launch {
            try {
                val offerCoupons = couponContentUseCase.loadOfferCoupons(offerCouponsPage, offersPageSize)
                offerCouponsPage++

                val updatedOfferCoupons = currentLoadedOfferCoupons + offerCoupons
                currentLoadedOfferCoupons.addAll(offerCoupons)

                withContext(Dispatchers.Default) {
                    offerCouponsState = MainScreenCouponLoadingState.Success(updatedOfferCoupons)
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