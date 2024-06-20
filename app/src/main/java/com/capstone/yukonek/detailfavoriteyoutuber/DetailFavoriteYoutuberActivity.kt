package com.capstone.yukonek.detailfavoriteyoutuber

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.capstone.yukonek.R
import com.capstone.yukonek.component.appbar.MyTopBar
import com.capstone.yukonek.component.card.CardListYoutuberRow
import com.capstone.yukonek.home.data.Channel
import com.capstone.yukonek.navigations.Screen
import com.capstone.yukonek.ui.theme.YuKonekTheme

class DetailFavoriteYoutuberActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            YuKonekTheme {
                MainViewDetailFavoriteYoutuber()
            }
        }
    }
}

@Composable
fun MainViewDetailFavoriteYoutuber(navController: NavHostController? = null) {
    val channels = listOf(
        Channel(name = "Jerome Polin", thumbnail = R.drawable.thumbnail, subscriber = "10,5M", category = "Education"),
        Channel(name = "Leonardo Edwin", thumbnail = R.drawable.leonardo, subscriber = "2M", category = "Education"),
        Channel(name = "Zahid Ibrahim", thumbnail = R.drawable.zahid, subscriber = "597K", category = "Education"),
        Channel(name = "Zhafira Aqyla", thumbnail = R.drawable.zhafira, subscriber = "356K", category = "Education"),
        Channel(name = "Naila Farhana", thumbnail = R.drawable.naila, subscriber = "799K", category = "Education"),
        Channel(name = "Turah Parthayana", thumbnail = R.drawable.turah, subscriber = "1,950M", category = "Education"),
        Channel(name = "Indah Gilang Pusparani", thumbnail = R.drawable.indah, subscriber = "2,870K", category = "Education"),
        Channel(name = "Nadhira Nuraini Afifa", thumbnail = R.drawable.nadhira, subscriber = "269K", category = "Education"),
        Channel(name = "Gita Savitri Devi", thumbnail = R.drawable.gita, subscriber = "1,340M", category = "Education"),
        Channel(name = "Raymond Chin", thumbnail = R.drawable.raymond, subscriber = "1,770M", category = "Education"),
        // Add more channels as needed
    )
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            MyTopBar(title = "Favorite YouTubers", onBackClick = {
                navController?.popBackStack()
            })
        },
    ) { innerPadding ->
        LazyColumn(modifier = Modifier.padding(innerPadding)) {
            items(channels.size) {
                CardListYoutuberRow(onClick = { navController?.navigate(Screen.DETAIL_YOUTUBER.name) },name = channels[it].name, thumbnail = channels[it].thumbnail, subscriber = channels[it].subscriber, category = channels[it].category)
                HorizontalDivider(thickness = 1.dp, modifier = Modifier.padding(horizontal = 16.dp))
            }

        }

    }
}

@Preview(showBackground = true)
@Composable
fun DetailFavoriteYotuberPreview() {
    YuKonekTheme {
        MainViewDetailFavoriteYoutuber()
    }
}
