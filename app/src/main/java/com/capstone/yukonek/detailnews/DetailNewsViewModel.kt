package com.capstone.yukonek.detailnews

import androidx.lifecycle.ViewModel
import com.capstone.yukonek.network.repository.YuKonekRepository
import kotlinx.coroutines.flow.Flow

class DetailNewsViewModel(private val yuKonekRepository: YuKonekRepository):ViewModel() {

//    fun getUrlNews(): Flow<String> = yuKonekRepository.getArguments("url")
}