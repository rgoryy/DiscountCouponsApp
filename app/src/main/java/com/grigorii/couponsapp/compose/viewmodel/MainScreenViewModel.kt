package com.grigorii.couponsapp.compose.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.viewModelScope
import com.grigorii.couponsapp.compose.domain.CouponContentUseCase
import com.grigorii.couponsapp.compose.model.CouponApi
import com.grigorii.couponsapp.compose.model.User
import com.grigorii.couponsapp.compose.repository.UserRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainScreenViewModel(
    couponContentUseCase: CouponContentUseCase = CouponContentUseCase()
) : AbstractCouponViewModel(couponContentUseCase) {

    val userRepository = UserRepository() //todo добавить UseCase

    var userLoadingInfoState by mutableStateOf<UserLoadingState>(
        UserLoadingState.Loading
    )
        private set

    var offerCouponsState by mutableStateOf<CouponLoadingState>(
        CouponLoadingState.Loading
    )
        private set

    var usersCouponsState by mutableStateOf<CouponLoadingState>(
        CouponLoadingState.Loading
    )
        private set


    private var offerCouponsPage = 0
    private var userCouponsPage = 0

    private val userPageSize = 3
    private val offersPageSize = 2

    private val currentLoadedOfferCoupons = mutableListOf<CouponApi>()
    private val currentLoadedUserCoupons  = mutableListOf<CouponApi>()

    fun fetchContent() {
        fetchUserInfo()
        fetchUserCoupons()
        fetchOfferCoupons()
    }

    fun fetchUserInfo() {
        viewModelScope.launch {
            try {
                val userInfo = userRepository.loadUserInfo()

                withContext(Dispatchers.Default) {
                    userLoadingInfoState = UserLoadingState.Success(userInfo)
                }
            } catch (e: Exception) {
                userLoadingInfoState = UserLoadingState.Error("Невозможно загрузить данные пользователя")
            }
        }

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
}

sealed class UserLoadingState {
    data object Loading : UserLoadingState()
    data class Success(val user: User) : UserLoadingState()
    data class Error(val message: String) : UserLoadingState()
}

