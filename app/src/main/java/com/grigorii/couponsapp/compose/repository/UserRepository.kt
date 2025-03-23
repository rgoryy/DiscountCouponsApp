package com.grigorii.couponsapp.compose.repository

import com.grigorii.couponsapp.compose.model.User
import kotlinx.coroutines.delay

class UserRepository {

    private val userName = "Григорий"

    private val userSurname = "Алексеев"

    private val userTown = "Томск"


    suspend fun loadUserInfo(): User {
        delay(500)
        return User(name = userName, surname = userSurname, town = userTown)
    }


    suspend fun loadUserName(): String {
        return userName
    }

    suspend fun loadUserSurname(): String {
        return userSurname
    }

    suspend fun loadUserTown(): String {
        return userTown
    }

}