package com.capstone.yukonek.di

import android.content.Context
import com.capstone.yukonek.local.datastore.SettingPreferencesDataStore
import com.capstone.yukonek.local.datastore.dataStore
import com.capstone.yukonek.network.repository.YuKonekRepository
import com.capstone.yukonek.network.retrofit.ApiConfig

object Injection {
    fun provideRepository(context: Context):YuKonekRepository{
        val preferences = SettingPreferencesDataStore.getInstance(context.dataStore)
        val apiService = ApiConfig.getApiService()
        return YuKonekRepository.getInstance(apiService,preferences)
    }
}