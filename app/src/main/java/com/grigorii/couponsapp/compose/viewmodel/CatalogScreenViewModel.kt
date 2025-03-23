package com.grigorii.couponsapp.compose.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.grigorii.couponsapp.compose.domain.CouponContentUseCase
import com.grigorii.couponsapp.compose.model.CouponApi


class CatalogScreenViewModel(
    couponContentUseCase: CouponContentUseCase = CouponContentUseCase()
) : AbstractCouponViewModel(couponContentUseCase) {

    var offerCouponsState by mutableStateOf<CouponLoadingState>(
        CouponLoadingState.Loading
    )
        private set

    private var offerCouponsPage = 1
    private val offersPageSize = 2

    private val currentLoadedOfferCoupons = mutableListOf<CouponApi>()

    fun fetchContent() {
        fetchOfferCoupons()
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
