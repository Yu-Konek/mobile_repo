package com.capstone.yukonek.editprofile

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.capstone.yukonek.di.Injection
import com.capstone.yukonek.network.repository.YuKonekRepository

class EditProfileViewModelFactory private constructor(private val yuKonekRepository: YuKonekRepository) :
    ViewModelProvider.NewInstanceFactory() {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(EditProfileViewModel::class.java)) {
            return EditProfileViewModel(yuKonekRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class : " + modelClass.name)
    }

    companion object {
        @Volatile
        private var instance: EditProfileViewModelFactory? = null
        fun getInstance(context: Context): EditProfileViewModelFactory =
            instance ?: synchronized(this) {
                instance ?: EditProfileViewModelFactory(Injection.provideRepository(context))
            }.also { instance = it }
    }
}