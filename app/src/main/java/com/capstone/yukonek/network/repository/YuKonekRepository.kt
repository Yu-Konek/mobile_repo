package com.capstone.yukonek.network.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.liveData
import com.capstone.yukonek.home.data.MResponseNews
import com.capstone.yukonek.local.datastore.SettingPreferencesDataStore
import com.capstone.yukonek.network.Result
import com.capstone.yukonek.network.error.ErrorResponse
import com.capstone.yukonek.network.retrofit.myapi.PrivateApiService
import com.capstone.yukonek.network.retrofit.newsapi.NewsApiService
import com.google.gson.Gson
import kotlinx.coroutines.flow.Flow
import retrofit2.HttpException

class YuKonekRepository private constructor(
    private val apiService: PrivateApiService,
    private val newsApiService: NewsApiService,
    private val pref: SettingPreferencesDataStore,
    private val savedStateHandle: SavedStateHandle
) {
    fun getTheme(): Flow<Boolean> {
        return pref.getThemeSetting()
    }

    suspend fun saveTheme(isDarkMode: Boolean) {
        pref.saveThemeSetting(isDarkMode)
    }

    fun getTopHeadlineEntertainmentNews(): LiveData<Result<MResponseNews>> =
        liveData {
            emit(Result.Loading)
            try {
                val response = newsApiService.getTopEntertainmentHeadlines()
                emit(Result.Success(response))
            } catch (e: HttpException) {
                val response = e.response()?.errorBody()?.string()
                val errorBody = Gson().fromJson(response, ErrorResponse::class.java)
                val errorMessage = errorBody.message
                emit(Result.Error(errorMessage.toString()))
            }
        }

    fun getArguments(arg: String): Flow<String> = checkNotNull(savedStateHandle[arg])


    companion object {
        @Volatile
        private var instance: YuKonekRepository? = null
        fun getInstance(
            apiService: PrivateApiService,
            newsApiService: NewsApiService,
            pref: SettingPreferencesDataStore,
            savedStateHandle: SavedStateHandle
        ): YuKonekRepository = instance ?: synchronized(this) {
            instance ?: YuKonekRepository(apiService, newsApiService, pref, savedStateHandle).also {
                instance = it
            }
        }

        private const val TAG = "YuKonekRepository"
    }
}