package com.grigorii.couponsapp.compose.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.grigorii.couponsapp.compose.domain.CouponContentUseCase
import com.grigorii.couponsapp.compose.model.Coupon
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class CatalogScreenViewModel : ViewModel() {

    var offerCouponsState by mutableStateOf<MainScreenCouponLoadingState>(
        MainScreenCouponLoadingState.Loading
    )
        private set

    private val couponContentUseCase = CouponContentUseCase()

    private var offerCouponsPage = 1
    private val offersPageSize = 2

    private val currentLoadedOfferCoupons = mutableListOf<Coupon>()

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


    private fun fetchCoupons(
        loadCoupons: suspend (Int, Int) -> List<Coupon>,
        page: Int,
        pageSize: Int,
        currentLoadedCoupons: MutableList<Coupon>,
        updateState: (MainScreenCouponLoadingState) -> Unit,
        errorMessage: String = "Ошибка загрузки данных"
    ): Int {
        viewModelScope.launch {
            try {
                val coupons = loadCoupons(page, pageSize)
                val updatedCoupons = currentLoadedCoupons + coupons
                currentLoadedCoupons.addAll(coupons)

                withContext(Dispatchers.Default) {
                    updateState(MainScreenCouponLoadingState.Success(updatedCoupons))
                }
            } catch (e: Exception) {
                updateState(MainScreenCouponLoadingState.Error(errorMessage))
            }

        }
        return page + 1
    }
}

sealed class CatalogScreenState {
    data object Loading : CatalogScreenState()
    data class Success(val coupons: List<Coupon>) : CatalogScreenState()
    data class Error(val message: String) : CatalogScreenState()
}