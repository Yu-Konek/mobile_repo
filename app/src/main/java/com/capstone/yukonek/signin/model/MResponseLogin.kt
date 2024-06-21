package com.capstone.yukonek.signin.model

import com.google.gson.annotations.SerializedName

data class MResponseLogin(

    @field:SerializedName("accessToken")
    val accessToken: String? = null,

    @field:SerializedName("username")
    val username: String? = null
)
