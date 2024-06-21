package com.capstone.yukonek.signin

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.capstone.yukonek.local.datastore.MUser
import com.capstone.yukonek.network.repository.YuKonekRepository
import kotlinx.coroutines.launch

class LoginViewModel(private val yuKonekRepository: YuKonekRepository): ViewModel() {

    private val _navigateToHomePage = MutableLiveData<Boolean>()
    val navigateToHomePage: LiveData<Boolean> get() = _navigateToHomePage
    fun login(email: String, password: String) = yuKonekRepository.login(email, password)

    fun saveTokenAndNavigate(user: MUser) {
        viewModelScope.launch {
            try {
                yuKonekRepository.saveUser(user)
                _navigateToHomePage.postValue(true)
            } catch (e: Exception) {
                Log.d("TAG", "Error saving token: ${e.message}")
            }
        }
    }
}