package com.capstone.yukonek.welcome

import androidx.lifecycle.ViewModel
import com.capstone.yukonek.network.repository.YuKonekRepository

class WelcomeViewModel(private val yuKonekRepository: YuKonekRepository): ViewModel() {
    fun getUser() = yuKonekRepository.getUser()
}