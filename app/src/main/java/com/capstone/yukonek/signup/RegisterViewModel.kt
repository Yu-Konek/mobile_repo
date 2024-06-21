package com.capstone.yukonek.signup

import androidx.lifecycle.ViewModel
import com.capstone.yukonek.network.repository.YuKonekRepository

class RegisterViewModel(private val yuKonekRepository: YuKonekRepository) : ViewModel() {
    fun register(name: String, email: String, password: String, confPassword: String) =
        yuKonekRepository.register(name, email, password, confPassword)
}