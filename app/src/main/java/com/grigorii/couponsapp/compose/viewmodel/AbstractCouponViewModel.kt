package com.grigorii.couponsapp.compose.viewmodel

import androidx.compose.runtime.Composable
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import coil.compose.rememberAsyncImagePainter
import com.grigorii.couponsapp.compose.domain.CouponContentUseCase
import com.grigorii.couponsapp.compose.model.CouponApi
import com.grigorii.couponsapp.compose.screens.CardItemContent
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

abstract class AbstractCouponViewModel (
    protected val couponContentUseCase: CouponContentUseCase
) : ViewModel() {
    protected fun fetchCoupons(
        loadCoupons: suspend (Int, Int) -> List<CouponApi>,
        page: Int,
        pageSize: Int,
        currentLoadedCoupons: MutableList<CouponApi>,
        updateState: (CouponLoadingState) -> Unit,
        errorMessage: String = "Ошибка загрузки данных"
    ): Int {
        viewModelScope.launch {
            try {
                val coupons = loadCoupons(page, pageSize)
                val updatedCoupons = currentLoadedCoupons + coupons
                currentLoadedCoupons.addAll(coupons)

                withContext(Dispatchers.Main) {
                    updateState(CouponLoadingState.Success(updatedCoupons))
                }
            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    updateState(CouponLoadingState.Error(e.message.toString()))
                }
            }
        }
        return page + 1
    }

    @Composable
    fun mapCouponToCardItemContent(coupon: CouponApi): CardItemContent {
        return CardItemContent(
            id = coupon.id,
            title = coupon.title ?: "",
            painter = rememberAsyncImagePainter(coupon.imageUrl),
            imageDescription = coupon.imageDescription ?: "",
            location = coupon.location ?: "",
            price = coupon.price ?: "",
            validityPeriod = coupon.validityPeriod ?: "",
            description = coupon.description ?: ""
        )
    }

}