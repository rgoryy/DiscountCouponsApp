package com.grigorii.couponsapp.compose.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.grigorii.couponsapp.compose.domain.CouponContentUseCase
import com.grigorii.couponsapp.compose.model.Coupon

class MainScreenViewModel(
    couponContentUseCase: CouponContentUseCase = CouponContentUseCase()
) : AbstractCouponViewModel(couponContentUseCase) {

    var offerCouponsState by mutableStateOf<CouponLoadingState>(
        CouponLoadingState.Loading
    )
        private set

    var usersCouponsState by mutableStateOf<CouponLoadingState>(
        CouponLoadingState.Loading
    )
        private set


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
        userCouponsPage = fetchCoupons(
            loadCoupons = { userCouponsPage, userPageSize ->
                couponContentUseCase.loadUserCoupons(userCouponsPage, userPageSize)
            },
            page = userCouponsPage,
            pageSize = userPageSize,
            currentLoadedCoupons = currentLoadedUserCoupons,
            updateState = { state -> usersCouponsState = state }
        )
    }

    fun fetchOfferCoupons() {
        offerCouponsPage = fetchCoupons(
            loadCoupons = { offerCouponsPage, offersPageSize ->
                couponContentUseCase.loadOfferCoupons(offerCouponsPage, offersPageSize)
            },
            page = offerCouponsPage,
            pageSize = offersPageSize,
            currentLoadedCoupons = currentLoadedOfferCoupons,
            updateState = { state -> offerCouponsState = state }
        )
    }
}

