package com.grigorii.couponsapp.compose.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.grigorii.couponsapp.compose.domain.CouponContentUseCase
import com.grigorii.couponsapp.compose.model.Coupon
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

abstract class AbstractCouponViewModel (
    protected val couponContentUseCase: CouponContentUseCase
) : ViewModel() {
    protected fun fetchCoupons(
        loadCoupons: suspend (Int, Int) -> List<Coupon>,
        page: Int,
        pageSize: Int,
        currentLoadedCoupons: MutableList<Coupon>,
        updateState: (CouponLoadingState) -> Unit,
        errorMessage: String = "Ошибка загрузки данных"
    ): Int {
        viewModelScope.launch {
            try {
                val coupons = loadCoupons(page, pageSize)
                val updatedCoupons = currentLoadedCoupons + coupons
                currentLoadedCoupons.addAll(coupons)

                withContext(Dispatchers.Default) {
                    updateState(CouponLoadingState.Success(updatedCoupons))
                }
            } catch (e: Exception) {
                updateState(CouponLoadingState.Error(errorMessage))
            }
        }
        return page + 1
    }
}