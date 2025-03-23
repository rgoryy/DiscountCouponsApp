package com.grigorii.couponsapp.compose.retrofit

import com.grigorii.couponsapp.compose.model.CouponApi
import com.grigorii.couponsapp.compose.model.CouponsResponse
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Path

interface CouponApiService {
    @GET("coupons")
    suspend fun getCoupons(@Body request: CouponRequest): CouponsResponse

    @GET("coupons/{id}")
    suspend fun getCouponById(@Path("id") id: Int): CouponApi
}

object RetrofitClient {
    private const val BASE_URL = "http://51.250.32.147:8080/api/"

    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val couponApiService: CouponApiService = retrofit.create(CouponApiService::class.java)
}