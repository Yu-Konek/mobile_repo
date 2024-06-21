package com.capstone.yukonek.welcome

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.capstone.yukonek.di.Injection
import com.capstone.yukonek.network.repository.YuKonekRepository

class WelcomeViewModelFactory(private val yuKonekRepository: YuKonekRepository) :
    ViewModelProvider.NewInstanceFactory() {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(WelcomeViewModel::class.java)) {
            return WelcomeViewModel(yuKonekRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class : " + modelClass.name)
    }

    companion object {
        @Volatile
        private var instance: WelcomeViewModelFactory? = null
        fun getInstance(context: Context): WelcomeViewModelFactory =
            instance ?: synchronized(this) {
                instance ?: WelcomeViewModelFactory(Injection.provideRepository(context))
            }.also { instance = it }
    }
}