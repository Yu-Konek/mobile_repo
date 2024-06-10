package com.capstone.yukonek.network.retrofit.newsapi

import com.capstone.yukonek.BuildConfig
import com.capstone.yukonek.network.retrofit.myapi.PrivateApiService
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class NewsApiConfig {
    companion object{
        private var BASE_URL = BuildConfig.BASE_URL_NEWS_API
        fun getApiService(): NewsApiService {
            val loggingInterceptor = if (BuildConfig.DEBUG) {
                HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
            } else {
                HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.NONE)
            }
            val client = OkHttpClient.Builder()
                .addInterceptor(loggingInterceptor)
                .build()

            val retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build()

            return retrofit.create(NewsApiService::class.java)
        }
    }
}