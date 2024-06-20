package com.capstone.yukonek.network.retrofit.myapi

import com.capstone.yukonek.network.retrofit.RequestBody.LoginRequest
import com.capstone.yukonek.network.retrofit.RequestBody.RegisterRequest
import com.capstone.yukonek.signin.model.MResponseLogin
import com.capstone.yukonek.signup.model.MResponseRegister
import retrofit2.http.Body
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface PrivateApiService {
    @POST("/users")
    suspend fun register(
        @Body request: RegisterRequest
    ) :MResponseRegister

    @POST("/login")
    suspend fun login(
        @Body request: LoginRequest
    ) :MResponseLogin
}