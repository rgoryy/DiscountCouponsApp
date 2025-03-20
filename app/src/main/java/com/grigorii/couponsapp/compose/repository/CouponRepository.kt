package com.grigorii.couponsapp.compose.repository

import com.grigorii.couponsapp.R
import com.grigorii.couponsapp.compose.model.Coupon
import kotlinx.coroutines.delay

class CouponRepository {
    private val offerCoupons = listOf(
        Coupon(
            title = "Консультации психолога",
            imageResourceId = R.drawable.psycholog,
            imageDescription = "Консультации психолога",
            location = "г. Томск",
            price = "2000 руб.",
            validityPeriod = "sss"
        ),
        Coupon(
            title = "Занятия по танцам",
            imageResourceId = R.drawable.sportclub,
            imageDescription = "Занятия по танцам",
            location = "г.Томск",
            price = "2000 руб.",
            validityPeriod = "sss"
        )
    )

    private val userCoupons = listOf(
        Coupon(
            title = "Курс “Разработка Android-приложений”",
            imageResourceId = R.drawable.android,
            imageDescription = "Консультации психолога",
            location = "г. Томск",
            price = "2000 руб.",
            validityPeriod = "действителен до 31.05.2025"
        ),
        Coupon(
            title = "Курс по английскому языку",
            imageResourceId = R.drawable.engl,
            imageDescription = "Курс по английскому языку",
            location = "г.Томск",
            price = "2000 руб.",
            validityPeriod = "действителен до 20.05.2025"
        ),
        Coupon(
            title = "Онлайн-курс по фотографии",
            imageResourceId = R.drawable.photo,
            imageDescription = "Онлайн-курс по фотографии",
            location = "г.Томск",
            price = "2000 руб.",
            validityPeriod = "действителен до 20.05.2025"
        ),
    )

    suspend fun loadOfferCoupons() : List<Coupon> {
        delay(1000)
        return offerCoupons
    }

    suspend fun loadUserCoupons() : List<Coupon> {
        delay(3000)
        return userCoupons
    }

    fun loadCouponById(id: Int): Coupon {
        TODO("not implemented yet")
    }
}