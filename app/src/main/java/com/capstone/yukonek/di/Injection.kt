package com.capstone.yukonek.di

import android.content.Context
import androidx.lifecycle.SavedStateHandle
import com.capstone.yukonek.local.datastore.SettingPreferencesDataStore
import com.capstone.yukonek.local.datastore.dataStore
import com.capstone.yukonek.local.room.YuKonekDatabase
import com.capstone.yukonek.network.repository.YuKonekRepository
import com.capstone.yukonek.network.retrofit.myapi.PrivateApiConfig
import com.capstone.yukonek.network.retrofit.newsapi.NewsApiConfig

object Injection {
    fun provideRepository(context: Context): YuKonekRepository {
        val preferences = SettingPreferencesDataStore.getInstance(context.dataStore)
        val apiService = PrivateApiConfig.getApiService()
        val newsApiService = NewsApiConfig.getApiService()
        val database = YuKonekDatabase.getInstance(context)
        return YuKonekRepository.getInstance(
            apiService, newsApiService, preferences,
            SavedStateHandle(), database
        )
    }
}