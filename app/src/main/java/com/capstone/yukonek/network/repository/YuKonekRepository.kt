package com.capstone.yukonek.network.repository

import com.capstone.yukonek.local.datastore.SettingPreferencesDataStore
import com.capstone.yukonek.network.retrofit.ApiService

class YuKonekRepository private constructor(
    private val apiService: ApiService,
    private val pref: SettingPreferencesDataStore
) {

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