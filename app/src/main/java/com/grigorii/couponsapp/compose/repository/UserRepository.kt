package com.grigorii.couponsapp.compose.repository

import android.content.Context
import android.content.SharedPreferences
import androidx.core.content.edit
import com.grigorii.couponsapp.compose.model.User
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class UserRepository(context: Context) {

    private val sharedPreferences: SharedPreferences =
        context.getSharedPreferences("user_data", Context.MODE_PRIVATE)

    companion object {
        private const val KEY_USER_NAME = "user_name"
        private const val KEY_USER_SURNAME = "user_surname"
        private const val KEY_USER_TOWN = "user_town"
    }

    fun isFirstLaunch(): Boolean {
        return sharedPreferences.getBoolean("is_first_launch", true)
    }

    fun setFirstLaunch() {
        sharedPreferences.edit {
            putBoolean("is_first_launch", false)
        }
    }

    suspend fun saveUserInfo(user: User) {
        withContext(Dispatchers.IO) {
            sharedPreferences.edit {
                putString(KEY_USER_NAME, user.name)
                putString(KEY_USER_SURNAME, user.surname)
                putString(KEY_USER_TOWN, user.town)
            }
        }
    }


    suspend fun loadUserInfo(): User {
        return withContext(Dispatchers.IO) {
            val name = sharedPreferences.getString(KEY_USER_NAME, "") ?: ""
            val surname = sharedPreferences.getString(KEY_USER_SURNAME, "") ?: ""
            val town = sharedPreferences.getString(KEY_USER_TOWN, "") ?: ""
            User(name, surname, town)
        }
    }

}