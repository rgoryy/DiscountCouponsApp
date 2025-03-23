package com.grigorii.couponsapp.compose.repository

import com.grigorii.couponsapp.compose.model.CouponApi
import com.grigorii.couponsapp.compose.retrofit.CouponRequest
import com.grigorii.couponsapp.compose.retrofit.RetrofitClient

class CouponApiRepository {
    suspend fun loadOfferCoupons(page: Int, pageSize: Int): List<CouponApi> {

        try {
            val response = RetrofitClient.couponApiService.getCoupons(
                CouponRequest(
                    page.toString(),
                    pageSize.toString()
                )
            )
            return response.coupons
        } catch (e: Exception) {
            println(e.message)
            throw Exception("Ошибка загрузки купонов: ${e.message}")
        }
    }

    suspend fun loadCouponById(id: Int): CouponApi {
        try {
            return RetrofitClient.couponApiService.getCouponById(id)
        } catch (e: Exception) {
            throw Exception("Ошибка загрузки купона: ${e.message}")
        }
    }
}