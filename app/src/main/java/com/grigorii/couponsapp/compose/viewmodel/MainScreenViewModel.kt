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

class MainScreenViewModel : ViewModel() {

    var offerCouponsState by mutableStateOf<MainScreenCouponLoadingState>(
        MainScreenCouponLoadingState.Loading
    )
        private set

    var usersCouponsState by mutableStateOf<MainScreenCouponLoadingState>(
        MainScreenCouponLoadingState.Loading
    )
        private set

    private val couponContentUseCase = CouponContentUseCase()

    private var offerCouponsPage = 1
    private var userCouponsPage = 1
    private val userPageSize = 3
    private val offersPageSize = 2

    private val currentLoadedOfferCoupons = mutableListOf<Coupon>()
    private val currentLoadedUserCoupons  = mutableListOf<Coupon>()

    fun fetchContent() {
        fetchUserCoupons()
        fetchOfferCoupons()
    }

    fun fetchUserCoupons() {
        userCouponsPage = fetchCoupons(
            loadCoupons = { userCouponsPage, userPageSize ->
                couponContentUseCase.loadUserCoupons(userCouponsPage, userPageSize)
            },
            page = userCouponsPage,
            pageSize = userPageSize,
            currentLoadedCoupons = currentLoadedUserCoupons,
            updateState = { state -> usersCouponsState = state }
        )
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

sealed class MainScreenCouponLoadingState {
    data object Loading : MainScreenCouponLoadingState()
    data class Success(val coupons: List<Coupon>) : MainScreenCouponLoadingState()
    data class Error(val message: String) : MainScreenCouponLoadingState()
}