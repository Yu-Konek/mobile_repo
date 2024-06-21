package com.capstone.yukonek.network.retrofit.RequestBody

data class RegisterRequest(
    val name: String,
    val email: String,
    val password: String,
    val confPassword: String
)