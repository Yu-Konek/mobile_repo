package com.capstone.yukonek.network.retrofit.newsapi

import com.capstone.yukonek.home.data.MResponseNewsApi
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApiService {

    @GET("top-headlines")
    suspend fun getTopEntertainmentHeadlines(
        @Query("country") country: String,
        @Query("category") category: String,
        @Query("apiKey") apiKey: String
    ): MResponseNewsApi
}