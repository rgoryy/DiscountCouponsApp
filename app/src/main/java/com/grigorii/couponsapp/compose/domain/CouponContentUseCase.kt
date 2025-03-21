package com.grigorii.couponsapp.compose.domain

import com.grigorii.couponsapp.compose.model.Coupon
import com.grigorii.couponsapp.compose.model.MainScreenContentData
import com.grigorii.couponsapp.compose.repository.CouponRepository

class CouponContentUseCase {
    private val couponRepository = CouponRepository()

    suspend fun loadContent(): MainScreenContentData {
        val offerCoupons = couponRepository.loadOfferCoupons()
        val userCoupons = couponRepository.loadUserCoupons()

        return MainScreenContentData(offerCoupons, userCoupons)
    }

    suspend fun loadOfferCoupons(): List<Coupon> {
        return couponRepository.loadOfferCoupons()
    }

    suspend fun loadUsersCoupons() : List<Coupon> {
        return couponRepository.loadUserCoupons()
    }

}