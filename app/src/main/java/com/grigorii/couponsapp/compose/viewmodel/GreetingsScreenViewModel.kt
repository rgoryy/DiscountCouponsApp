package com.grigorii.couponsapp.compose.viewmodel

import android.app.Application
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.grigorii.couponsapp.compose.model.User
import com.grigorii.couponsapp.compose.repository.UserRepository
import kotlinx.coroutines.launch

class GreetingsScreenViewModel(application: Application): ViewModel() {

    private val userRepository = UserRepository(context = application.applicationContext)

    var userCity by mutableStateOf("")
        private set
    var userName by mutableStateOf("")
        private set
    var userSurname by mutableStateOf("")
        private set

    var userLoadingInfoState by mutableStateOf<UserLoadingState>(UserLoadingState.Loading)
        private set



    fun saveUserCity(city: String) {
        userCity = city
        saveUserInfo(User(userName, userSurname, userCity))
    }

    fun saveUserName(name: String) {
        userName = name
        saveUserInfo(User(userName, userSurname, userCity))
    }

    fun saveUserSurname(surname: String) {
        userSurname = surname
        saveUserInfo(User(userName, userSurname, userCity))
    }


    fun saveUserInfo(user: User) {
        viewModelScope.launch {
            try {
                userRepository.saveUserInfo(user)
                userLoadingInfoState = UserLoadingState.Success(user)
            } catch (e: Exception) {
                userLoadingInfoState = UserLoadingState.Error("Ошибка при сохранении данных пользователя")
            }
        }
    }

}