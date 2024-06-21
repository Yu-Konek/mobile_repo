package com.capstone.yukonek.detailyoutuber

import androidx.lifecycle.ViewModel
import com.capstone.yukonek.network.repository.YuKonekRepository

class DetailYoutuberViewModel(private val yuKonekRepository: YuKonekRepository):ViewModel() {
    fun getDetailYoutuber(id:String) = yuKonekRepository.getDetailYoutuber(id)

}