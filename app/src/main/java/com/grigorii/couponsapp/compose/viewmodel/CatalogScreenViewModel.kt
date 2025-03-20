package com.grigorii.couponsapp.compose.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.grigorii.couponsapp.compose.domain.CouponContentUseCase
import com.grigorii.couponsapp.compose.model.Coupon
import com.grigorii.couponsapp.compose.model.MainScreenContentData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class CatalogScreenViewModel : ViewModel() {

    private val couponContentUseCase = CouponContentUseCase()

    var uiState by mutableStateOf<CatalogScreenState>(CatalogScreenState.Loading)
        private set


    fun fetchContent() {
        uiState = CatalogScreenState.Loading

        viewModelScope.launch {
            try {
                val catalogScreenContentData = couponContentUseCase.loadOfferCoupons()
                withContext(Dispatchers.Default) {
                    uiState = CatalogScreenState.Success(catalogScreenContentData)
                }
            } catch (e: Exception) {
                uiState = CatalogScreenState.Error("Ошибка загрузки данных")
            }
        }
    }
}

sealed class CatalogScreenState {
    data object Loading : CatalogScreenState()
    // есть необходимость делать отдельный DTO CatalogScreenContentData?
    data class Success(val coupons: List<Coupon>) : CatalogScreenState()
    data class Error(val message: String) : CatalogScreenState()
}