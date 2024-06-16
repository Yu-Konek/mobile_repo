package com.capstone.yukonek.network.retrofit.youtubeapi

import com.capstone.yukonek.detailyoutuber.data.MResponseDetailChannel
import com.capstone.yukonek.home.data.MResponseChannelList
import retrofit2.http.GET
import retrofit2.http.Query

interface YoutubeApiService {

    @GET("search")
    suspend fun getChannelByName(
        @Query("part") part: String = "snippet",
        @Query("type") type: String = "channel",
        @Query("q") channelName: String,
        @Query("key") apiKey: String
    ):MResponseChannelList

    @GET("channels")
    suspend fun getDetailChannel(
        @Query("part") part: String = "snippet,statistics",
        @Query("id") channelId: String,
        @Query("key") apiKey: String
    ):MResponseDetailChannel
}