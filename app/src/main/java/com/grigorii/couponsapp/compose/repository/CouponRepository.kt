package com.grigorii.couponsapp.compose.repository

import com.grigorii.couponsapp.compose.model.CouponApi
import kotlinx.coroutines.delay

class CouponRepository {
    private val offerCoupons = listOf(
        CouponApi(
            id = 1,
            title = "Консультации психолога",
            imageUrl = "f",
            imageDescription = "Консультации психолога",
            location = "г. Томск",
            price = "2000 руб.",
            validityPeriod = "sss",
            description = "Получите скидочный купон на консультацию у профессионального психолога! " +
                    "Погрузитесь в мир самопознания и улучшите свое психическое здоровье по специальной цене. " +
                    "Не упустите возможность сделать шаг к лучшей версии себя!\u2028Получите скидочный " +
                    "купон на консультацию у профессионального психолога! Получите скидочный купон на " +
                    "консультацию у профессионального психолога! Погрузитесь в мир самопознания и улучшите " +
                    "свое психическое здоровье по специальной цене. Не упустите возможность сделать " +
                    "шаг к лучшей версии себя!\u2028Получите скидочный купон на консультацию у " +
                    "профессионального психолога!"
        ),
        CouponApi(
            id = 2,
            title = "Занятия по танцам",
            imageUrl = "f",
            imageDescription = "Занятия по танцам",
            location = "г.Томск",
            price = "2000 руб.",
            validityPeriod = "sss"
        ),CouponApi(
            id = 3,
            title = "Курс “Разработка Android-приложений”",
            imageUrl = "f",
            imageDescription = "Консультации психолога",
            location = "г. Томск",
            price = "2000 руб.",
            validityPeriod = "действителен до 31.05.2025"
        ),
        CouponApi(
            id = 4,
            title = "Курс по английскому языку",
            imageUrl = "f",
            imageDescription = "Курс по английскому языку",
            location = "г.Томск",
            price = "2000 руб.",
            validityPeriod = "действителен до 20.05.2025"
        ),
        CouponApi(
            id = 5,
            title = "Онлайн-курс по фотографии",
            imageUrl = "f",
            imageDescription = "Онлайн-курс по фотографии",
            location = "г.Томск",
            price = "2000 руб.",
            validityPeriod = "действителен до 20.05.2025"
        ),
    )

    private val userCoupons = listOf(
        CouponApi(
            id = 6,
            title = "Курс “Разработка Android-приложений”",
            imageUrl = "f",
            imageDescription = "Консультации психолога",
            location = "г. Томск",
            price = "2000 руб.",
            validityPeriod = "действителен до 31.05.2025"
        ),
        CouponApi(
            id = 7,
            title = "Курс по английскому языку",
            imageUrl = "f",
            imageDescription = "Курс по английскому языку",
            location = "г.Томск",
            price = "2000 руб.",
            validityPeriod = "действителен до 20.05.2025"
        ),
        CouponApi(
            id = 8,
            title = "Онлайн-курс по фотографии",
            imageUrl = "f",
            imageDescription = "Онлайн-курс по фотографии",
            location = "г.Томск",
            price = "2000 руб.",
            validityPeriod = "действителен до 20.05.2025"
        ),
    )

    private val allCoupons = offerCoupons + userCoupons

    suspend fun loadOfferCoupons() : List<CouponApi> {
        delay(5000)
        return offerCoupons
    }

    suspend fun loadUserCoupons() : List<CouponApi> {
        delay(1000)
        return userCoupons
    }

    suspend fun loadOfferCoupons(page: Int, pageSize: Int): List<CouponApi> {
        delay(2000)
        //временное решение
        return offerCoupons
    }

    suspend fun loadUserCoupons(page: Int, pageSize: Int): List<CouponApi> {
        //временное решение
        return userCoupons
    }

    fun loadCouponById(id: Int): CouponApi? {
        return allCoupons.find { it.id == id }
    }
}