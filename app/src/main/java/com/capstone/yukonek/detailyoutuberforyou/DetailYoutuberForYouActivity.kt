package com.capstone.yukonek.detailyoutuberforyou

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
import com.capstone.yukonek.home.data.channels
import com.capstone.yukonek.navigations.Screen
import com.capstone.yukonek.ui.theme.YuKonekTheme


class DetailYoutuberForYou : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            YuKonekTheme {
                MainViewDetailYoutuberForYou()
            }
        }
    }
}

@Composable
fun MainViewDetailYoutuberForYou(navController: NavHostController? = null) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            MyTopBar(title = "YouTubers For You", onBackClick = {
                navController?.popBackStack()
            })
        },
    ) { innerPadding ->
        LazyColumn(modifier = Modifier.padding(innerPadding)) {
            items(channels.size) {
                CardListYoutuberRow(onClick = { navController?.navigate("${Screen.DETAIL_YOUTUBER.name}/${channels[it].id}") },name = channels[it].name, thumbnail = channels[it].thumbnail, subscriber = channels[it].subscriber, category = channels[it].category)
                HorizontalDivider(thickness = 1.dp, modifier = Modifier.padding(horizontal = 16.dp))
            }

        }

    }
}


@Preview(showBackground = true)
@Composable
fun DetailYoutuberForYourPreview() {
    YuKonekTheme {
        MainViewDetailYoutuberForYou()
    }
}

@Preview()
@Composable
fun PreviewCardListYoutuber() {
    CardListYoutuberRow(onClick = {  },name = "channels[it].name", thumbnail = R.drawable.thumbnail, subscriber = "channels[it].subscriber", category = "channels[it].category")
}
