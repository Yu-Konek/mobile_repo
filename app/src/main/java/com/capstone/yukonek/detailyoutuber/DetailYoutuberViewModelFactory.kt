package com.capstone.yukonek.detailyoutuber

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.capstone.yukonek.di.Injection
import com.capstone.yukonek.mainscreen.MainViewmodel

class DetailYoutuberViewModelFactory(private val context:Context):ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(DetailYoutuberViewModel::class.java)){
            return DetailYoutuberViewModel(Injection.provideRepository(context)) as T
        }
        throw IllegalArgumentException("Unknown ViewModel Class")
    }
}