package com.capstone.yukonek.di

import android.content.Context
import com.capstone.yukonek.local.datastore.SettingPreferencesDataStore
import com.capstone.yukonek.local.datastore.dataStore
import com.capstone.yukonek.network.repository.YuKonekRepository
import com.capstone.yukonek.network.retrofit.myapi.PrivateApiConfig

object Injection {
    fun provideRepository(context: Context):YuKonekRepository{
        val preferences = SettingPreferencesDataStore.getInstance(context.dataStore)
        val apiService = PrivateApiConfig.getApiService()
        return YuKonekRepository.getInstance(apiService,preferences)
    }
}