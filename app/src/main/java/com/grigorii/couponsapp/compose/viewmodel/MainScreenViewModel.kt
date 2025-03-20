package com.grigorii.couponsapp.compose.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.grigorii.couponsapp.compose.model.MainScreenContentData
import com.grigorii.couponsapp.compose.domain.CouponContentUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainScreenViewModel : ViewModel() {

    private val couponContentUseCase = CouponContentUseCase()

    var uiState by mutableStateOf<MainScreenState>(MainScreenState.Loading)
        private set


    fun fetchContent() {
        uiState = MainScreenState.Loading

        viewModelScope.launch {
            val mainScreenContentData = couponContentUseCase.loadContent()

            withContext(Dispatchers.Default) {
                uiState = MainScreenState.Success(mainScreenContentData)
            }
        }
    }
}

sealed class MainScreenState {
    data object Loading : MainScreenState()
    data class Success(val mainScreenData: MainScreenContentData) : MainScreenState()
}