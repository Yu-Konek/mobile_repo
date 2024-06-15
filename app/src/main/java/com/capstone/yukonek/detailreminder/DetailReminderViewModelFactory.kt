package com.capstone.yukonek.detailreminder

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.capstone.yukonek.di.Injection
import com.capstone.yukonek.network.repository.YuKonekRepository

class DetailReminderViewModelFactory(private val yuKonekRepository: YuKonekRepository) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(DetailReminderViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return DetailReminderViewModel(yuKonekRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
    companion object {
        @Volatile
        private var instance: DetailReminderViewModelFactory? = null
        fun getInstance(context: Context): DetailReminderViewModelFactory =
            instance ?: synchronized(this) {
                instance ?: DetailReminderViewModelFactory(Injection.provideRepository(context))
            }.also { instance = it }
    }
}