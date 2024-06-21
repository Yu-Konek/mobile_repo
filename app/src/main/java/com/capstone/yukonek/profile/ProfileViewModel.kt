package com.capstone.yukonek.profile

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.capstone.yukonek.network.repository.YuKonekRepository
import kotlinx.coroutines.launch

class ProfileViewModel(private val yuKonekRepository: YuKonekRepository): ViewModel() {

    private val _navigateToLoginPage = MutableLiveData<Boolean>()
    val navigateToLoginPage: LiveData<Boolean> get() = _navigateToLoginPage

    init {
        _navigateToLoginPage.value = false
    }
//    fun getUser() = yuKonekRepository.getUser()

    fun getThemeSettings() = yuKonekRepository.getTheme()

    fun getUserData() = yuKonekRepository.getUserData()

    fun setThemeSettings(isDarkModeActive: Boolean) {
        viewModelScope.launch {
            yuKonekRepository.saveTheme(isDarkModeActive)
        }
    }

    fun logout() {
        viewModelScope.launch {
            try {
                yuKonekRepository.clearUser()
                _navigateToLoginPage.postValue(true)
            } catch (_: Exception) {

            }
        }
    }
}