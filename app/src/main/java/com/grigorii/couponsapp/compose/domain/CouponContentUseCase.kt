package com.grigorii.couponsapp.compose.domain

import com.grigorii.couponsapp.compose.model.CouponApi
import com.grigorii.couponsapp.compose.repository.CouponApiRepository
import com.grigorii.couponsapp.compose.repository.CouponRepository

class CouponContentUseCase {
    private val couponURepository = CouponRepository()

    private val couponRepository = CouponApiRepository()

    /*suspend fun loadOfferCoupons(page: Int, pageSize: Int): List<Coupon> {
        return couponRepository.loadOfferCoupons(page, pageSize)
    }*/

    suspend fun loadOfferCoupons(page: Int, pageSize: Int): List<CouponApi> {
        return couponRepository.loadOfferCoupons(page, pageSize)
    }

    suspend fun loadCouponById(id: Int): CouponApi? {
        return couponRepository.loadCouponById(id)
    }

    suspend fun loadUserCoupons(page: Int, pageSize: Int): List<CouponApi> {
        return couponURepository.loadUserCoupons(page, pageSize)
    }

    /*suspend fun loadCouponById(id: Int): Coupon? {
        return couponRepository.loadCouponById(id)
    }*/
}