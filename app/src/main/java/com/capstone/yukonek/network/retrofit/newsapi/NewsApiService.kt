package com.capstone.yukonek.network.retrofit.newsapi

import com.capstone.yukonek.home.data.MResponseNews
import retrofit2.http.GET

interface NewsApiService {

    @GET("cnn/hiburan")
    suspend fun getTopEntertainmentHeadlines(
    ): MResponseNews
}