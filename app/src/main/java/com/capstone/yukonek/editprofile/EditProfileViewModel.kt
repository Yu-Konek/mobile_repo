package com.capstone.yukonek.editprofile

import androidx.lifecycle.ViewModel
import com.capstone.yukonek.network.repository.YuKonekRepository

class EditProfileViewModel(private val yuKonekRepository: YuKonekRepository): ViewModel() {
    fun getUserData() = yuKonekRepository.getUserData()
}