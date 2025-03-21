package com.grigorii.couponsapp.compose.repository

import com.grigorii.couponsapp.R
import com.grigorii.couponsapp.compose.model.Coupon
import kotlinx.coroutines.delay

class CouponRepository {
    private val offerCoupons = listOf(
        Coupon(
            id = 1,
            title = "Консультации психолога",
            imageResourceId = R.drawable.psycholog,
            imageDescription = "Консультации психолога",
            location = "г. Томск",
            price = "2000 руб.",
            validityPeriod = "sss"
        ),
        Coupon(
            id = 2,
            title = "Занятия по танцам",
            imageResourceId = R.drawable.sportclub,
            imageDescription = "Занятия по танцам",
            location = "г.Томск",
            price = "2000 руб.",
            validityPeriod = "sss"
        ),Coupon(
            id = 3,
            title = "Курс “Разработка Android-приложений”",
            imageResourceId = R.drawable.android,
            imageDescription = "Консультации психолога",
            location = "г. Томск",
            price = "2000 руб.",
            validityPeriod = "действителен до 31.05.2025"
        ),
        Coupon(
            id = 4,
            title = "Курс по английскому языку",
            imageResourceId = R.drawable.engl,
            imageDescription = "Курс по английскому языку",
            location = "г.Томск",
            price = "2000 руб.",
            validityPeriod = "действителен до 20.05.2025"
        ),
        Coupon(
            id = 5,
            title = "Онлайн-курс по фотографии",
            imageResourceId = R.drawable.photo,
            imageDescription = "Онлайн-курс по фотографии",
            location = "г.Томск",
            price = "2000 руб.",
            validityPeriod = "действителен до 20.05.2025"
        ),
    )

    private val userCoupons = listOf(
        Coupon(
            id = 3,
            title = "Курс “Разработка Android-приложений”",
            imageResourceId = R.drawable.android,
            imageDescription = "Консультации психолога",
            location = "г. Томск",
            price = "2000 руб.",
            validityPeriod = "действителен до 31.05.2025"
        ),
        Coupon(
            id = 4,
            title = "Курс по английскому языку",
            imageResourceId = R.drawable.engl,
            imageDescription = "Курс по английскому языку",
            location = "г.Томск",
            price = "2000 руб.",
            validityPeriod = "действителен до 20.05.2025"
        ),
        Coupon(
            id = 5,
            title = "Онлайн-курс по фотографии",
            imageResourceId = R.drawable.photo,
            imageDescription = "Онлайн-курс по фотографии",
            location = "г.Томск",
            price = "2000 руб.",
            validityPeriod = "действителен до 20.05.2025"
        ),
    )

    private val allCoupons = offerCoupons + userCoupons

    suspend fun loadOfferCoupons() : List<Coupon> {
        delay(5000)
        return offerCoupons
    }

    suspend fun loadUserCoupons() : List<Coupon> {
        delay(1000)
        return userCoupons
    }

    suspend fun loadOfferCoupons(page: Int, pageSize: Int): List<Coupon> {
        delay(2000)
        //временное решение
        return offerCoupons.drop((page - 1) * pageSize).take(pageSize)
    }

    suspend fun loadUserCoupons(page: Int, pageSize: Int): List<Coupon> {
        delay(1000)
        //временное решение
        return userCoupons.drop((page - 1) * pageSize).take(pageSize)
    }

    fun loadCouponById(id: Int): Coupon? {
        return allCoupons.find { it.id == id }
    }
}