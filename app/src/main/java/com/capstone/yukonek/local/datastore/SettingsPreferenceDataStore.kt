package com.capstone.yukonek.local.datastore

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")

class SettingPreferencesDataStore private constructor(private val dataStore: DataStore<Preferences>) {
    private val THEME_KEY = booleanPreferencesKey("theme_setting")

    fun getThemeSetting(): Flow<Boolean> {
        return dataStore.data.map { preferences ->
            preferences[THEME_KEY] ?: false
        }
    }

    suspend fun saveThemeSetting(isDarkModeActive: Boolean) {
        dataStore.edit { preferences ->
            preferences[THEME_KEY] = isDarkModeActive
        }
    }

    companion object {
        @Volatile
        private var INSTANCE: SettingPreferencesDataStore? = null

        private val NAME_KEY = stringPreferencesKey("name")
        private val TOKEN_KEY = stringPreferencesKey("token")
        private val IS_LOGIN_KEY = booleanPreferencesKey("isLogin")

        fun getInstance(dataStore: DataStore<Preferences>): SettingPreferencesDataStore {
            return INSTANCE ?: synchronized(this) {
                val instance = SettingPreferencesDataStore(dataStore)
                INSTANCE = instance
                instance
            }
        }
    }

    suspend fun saveUser(user: MUser) {
        dataStore.edit { preferences ->
            preferences[TOKEN_KEY] = user.token
//            preferences[NAME_KEY] ?: user.name
            preferences[IS_LOGIN_KEY] = true
        }
    }

    fun getUser(): Flow<MUser> {
        return dataStore.data.map { prefences ->
            MUser(
                prefences[TOKEN_KEY] ?: "",
//                prefences[NAME_KEY] ?: "",
                prefences[IS_LOGIN_KEY] ?: false,
            )
        }
    }

    suspend fun logout() {
        dataStore.edit { preferences ->
//            preferences.clear()
            preferences[TOKEN_KEY] = ""
            preferences[IS_LOGIN_KEY] = false
        }
    }

}