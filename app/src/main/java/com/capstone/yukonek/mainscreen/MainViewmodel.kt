package com.capstone.yukonek.mainscreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.capstone.yukonek.local.datastore.SettingPreferencesDataStore
import com.capstone.yukonek.network.repository.YuKonekRepository
import kotlinx.coroutines.launch

class MainViewmodel(private val yuKonekRepository: YuKonekRepository) : ViewModel() {

    fun getTopHeadlineEntertainement() =
        yuKonekRepository.getTopHeadlineEntertainmentNews()

    fun getUserData() = yuKonekRepository.getUserData()

}