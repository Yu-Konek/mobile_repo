package com.capstone.yukonek.mainscreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.capstone.yukonek.local.datastore.SettingPreferencesDataStore
import com.capstone.yukonek.network.repository.YuKonekRepository
import kotlinx.coroutines.launch

class MainViewmodel(private val yuKonekRepository: YuKonekRepository):ViewModel() {
    fun getThemeSettings() = yuKonekRepository.getTheme()
    fun setThemeSettings(isDarkModeActive: Boolean) {
        viewModelScope.launch {
            yuKonekRepository.saveTheme(isDarkModeActive)
        }
    }
}