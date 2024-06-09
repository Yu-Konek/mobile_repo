package com.capstone.yukonek.network.repository

import androidx.compose.runtime.collectAsState
import androidx.lifecycle.asLiveData
import com.capstone.yukonek.local.datastore.SettingPreferencesDataStore
import com.capstone.yukonek.network.retrofit.ApiService
import kotlinx.coroutines.flow.Flow

class YuKonekRepository private constructor(
    private val apiService: ApiService,
    private val pref: SettingPreferencesDataStore
) {
     fun getTheme(): Flow<Boolean> {
        return pref.getThemeSetting()
    }
    suspend fun saveTheme(isDarkMode: Boolean){
        pref.saveThemeSetting(isDarkMode)
    }
    companion object {
        @Volatile
        private var instance: YuKonekRepository? = null
        fun getInstance(
            apiService: ApiService,
            pref: SettingPreferencesDataStore,
        ): YuKonekRepository = instance ?: synchronized(this) {
            instance ?: YuKonekRepository(apiService, pref).also {
                instance = it
            }
        }

        private const val TAG = "YuKonekRepository"
    }
}