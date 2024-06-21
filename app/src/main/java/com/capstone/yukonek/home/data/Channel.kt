package com.capstone.yukonek.home.data

import com.capstone.yukonek.R

data class Channel(
    val id:String,
    val name: String,
    val thumbnail: Int,
    val subscriber: String,
    val category: String
)
val channels = listOf(
    Channel(id = "UC1Gmqqs_Myzl2KHIeqfUC9A",name = "Jerome Polin", thumbnail = R.drawable.thumbnail, subscriber = "10,5M", category = "Education"),
    Channel(id = "UCnNTNz5-uay303Zs-mALHcQ",name = "Leonardo Edwin", thumbnail = R.drawable.leonardo, subscriber = "2M", category = "Education"),
    Channel(id = "UCOAAruIpwf5dhOiYM4s7Krg",name = "Zahid Ibrahim", thumbnail = R.drawable.zahid, subscriber = "597K", category = "Education"),
    Channel(id = "UCQ4oBk0ujqeBGSwvevW_PeA",name = "Zhafira Aqyla", thumbnail = R.drawable.zhafira, subscriber = "356K", category = "Education"),
    Channel(id = "UCGBacngyYW4Nl2GafoVGKXg",name = "Naila Farhana", thumbnail = R.drawable.naila, subscriber = "799K", category = "Education"),
    Channel(id = "UCDCBzQLR7UrHxDUU1pHHorw",name = "Turah Parthayana", thumbnail = R.drawable.turah, subscriber = "1,950M", category = "Education"),
    Channel(id = "UCw-yny6E2CCMq5BR5nSyMog",name = "Indah Gilang Pusparani", thumbnail = R.drawable.indah, subscriber = "2,870K", category = "Education"),
    Channel(id = "UC5ho3doJAEi6jHCgBkDBkRQ",name = "Nadhira Nuraini Afifa", thumbnail = R.drawable.nadhira, subscriber = "269K", category = "Education"),
    Channel(id = "UCtECrfmHyJ0n32jSUfxcvXg",name = "Gita Savitri Devi", thumbnail = R.drawable.gita, subscriber = "1,340M", category = "Education"),
    Channel(id = "UCm4eA46c6boHmPrD0Lp2iEg",name = "Raymond Chin", thumbnail = R.drawable.raymond, subscriber = "1,770M", category = "Education"),
    // Add more channels as needed
)