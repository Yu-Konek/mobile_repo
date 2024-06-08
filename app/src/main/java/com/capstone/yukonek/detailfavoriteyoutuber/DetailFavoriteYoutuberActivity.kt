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
import com.capstone.yukonek.component.appbar.MyTopBar
import com.capstone.yukonek.component.card.CardListYoutuberRow
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
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            MyTopBar(title = "Favorite YouTubers",onBackClick = {
                navController?.popBackStack()
            })
        },
    ) { innerPadding->
        LazyColumn(modifier = Modifier.padding(innerPadding)){
            items(10){
                CardListYoutuberRow()
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
