package com.capstone.yukonek.network.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.asLiveData
import androidx.lifecycle.liveData
import com.capstone.yukonek.BuildConfig
import com.capstone.yukonek.detailyoutuber.data.MResponseDetailChannel
import com.capstone.yukonek.home.data.MResponseNews
import com.capstone.yukonek.home.data.TodoItem
import com.capstone.yukonek.local.datastore.MUser
import com.capstone.yukonek.local.datastore.SettingPreferencesDataStore
import com.capstone.yukonek.local.room.YuKonekDatabase
import com.capstone.yukonek.network.Result
import com.capstone.yukonek.network.error.ErrorResponse
import com.capstone.yukonek.network.retrofit.RequestBody.LoginRequest
import com.capstone.yukonek.network.retrofit.RequestBody.RegisterRequest
import com.capstone.yukonek.network.retrofit.myapi.PrivateApiService
import com.capstone.yukonek.network.retrofit.newsapi.NewsApiService
import com.capstone.yukonek.network.retrofit.youtubeapi.YoutubeApiService

import com.capstone.yukonek.signin.model.MResponseLogin
import com.capstone.yukonek.signup.model.MResponseRegister
import com.google.gson.Gson
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.toCollection
import retrofit2.HttpException

class YuKonekRepository private constructor(
    private val apiService: PrivateApiService,
    private val newsApiService: NewsApiService,
    private val youtubeApiService: YoutubeApiService,
    private val pref: SettingPreferencesDataStore,
    private val savedStateHandle: SavedStateHandle,
    private val database: YuKonekDatabase
) {
    fun getTheme(): Flow<Boolean> {
        return pref.getThemeSetting()
    }

    suspend fun saveTheme(isDarkMode: Boolean) {
        pref.saveThemeSetting(isDarkMode)
    }

    fun register(
        name: String, email: String, password: String, confPassword: String
    ): LiveData<Result<MResponseRegister>> = liveData {
        emit(Result.Loading)
        try {
            val request = RegisterRequest(name, email, password, confPassword)
            val response = apiService.register(request)
            emit(Result.Success(response))
        } catch (e: HttpException) {
            val jsonInString = e.response()?.errorBody()?.string()
            val errorBody = Gson().fromJson(jsonInString, ErrorResponse::class.java)
            val errorMessage = errorBody.message
            emit(Result.Error(errorMessage.toString()))
        } catch (e: Exception) {
            emit(Result.Error(e.message ?: "Unknown error occurred"))
        }
    }

    fun login(
        email: String, password: String
    ): LiveData<Result<MResponseLogin>> = liveData {
        emit(Result.Loading)
        try {
            val request = LoginRequest(email, password)
            val response = apiService.login(request)
            emit(Result.Success(response))
        } catch (e: HttpException) {
            val jsonInString = e.response()?.errorBody()?.string()
            val errorBody = Gson().fromJson(jsonInString, ErrorResponse::class.java)
            val errorMessage = errorBody.message
            emit(Result.Error(errorMessage.toString()))
        } catch (e: Exception) {
            emit(Result.Error(e.message ?: "Unknown error occurred"))
        }
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

    fun getDetailYoutuber(id:String): LiveData<Result<MResponseDetailChannel>> =
        liveData {
            emit(Result.Loading)
            try{
                val response = youtubeApiService.getDetailChannel(channelId = id, apiKey = BuildConfig.YOUTUBE_API_KEY)
                emit(Result.Success(response))
            }catch (e: HttpException){
                val response = e.response()?.errorBody()?.string()
                val errorBody = Gson().fromJson(response, ErrorResponse::class.java)
                val errorMessage = errorBody.message
                emit(Result.Error(errorMessage.toString()))
            }
        }
    fun getUser(): LiveData<MUser> {
        return pref.getUser().asLiveData()
    }

    suspend fun saveUser(user: MUser) {
        pref.saveUser(user)
    }

    fun getArguments(arg: String): Flow<String> = checkNotNull(savedStateHandle[arg])

    fun getAllTodoItems(): Flow<List<TodoItem>> {
        return database.todoDao().getAllTodoItem()
    }

    suspend fun insertTodoItems(todoItem: TodoItem) {
        database.todoDao().insertTodoItem(todoItem)
    }

    suspend fun deleteTodoItems(todoItem: TodoItem) {
        database.todoDao().deleteTodoItem(todoItem)
    }

    suspend fun updateTodoItems(todoItem: TodoItem) {
        database.todoDao().updateTodoItem(todoItem)
    }

    suspend fun clearUser() {
        pref.logout()
    }


    companion object {
        @Volatile
        private var instance: YuKonekRepository? = null
        fun getInstance(
            apiService: PrivateApiService,
            newsApiService: NewsApiService,
            youtubeApiService: YoutubeApiService,
            pref: SettingPreferencesDataStore,
            savedStateHandle: SavedStateHandle,
            database: YuKonekDatabase
        ): YuKonekRepository = instance ?: synchronized(this) {
            instance ?: YuKonekRepository(
                apiService,
                newsApiService,
                youtubeApiService,
                pref,
                savedStateHandle,
                database
            ).also {
                instance = it
            }
        }

        private const val TAG = "YuKonekRepository"
    }
}