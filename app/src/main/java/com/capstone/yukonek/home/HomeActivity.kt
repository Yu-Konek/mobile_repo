package com.capstone.yukonek.home

import android.content.res.Configuration
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.capstone.yukonek.R
import com.capstone.yukonek.component.reminder.TodoItemUi
import com.capstone.yukonek.home.data.TodoItem
import com.capstone.yukonek.navigations.Screen
import com.capstone.yukonek.ui.theme.MediumDp
import com.example.compose.YuKonekTheme
import com.example.ui.theme.AppTypography

class HomeActivity: ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            YuKonekTheme {
                MainViewHome()
            }
        }
    }
}

@Composable
fun MainViewHome(navController: NavHostController? = null) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        containerColor = MaterialTheme.colorScheme.primary
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()

            ) {
                Column(modifier = Modifier.padding(top = 24.dp)) {
                    CardDisplayName()
                }

                // Content area (Placeholder for main content)
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(innerPadding)
                        .clip(shape = RoundedCornerShape(20.dp, 20.dp, 0.dp, 0.dp))
                        .background(MaterialTheme.colorScheme.surface),
                ) {
                    LazyColumn(
                        modifier = Modifier
                            .padding(horizontal = 16.dp, vertical = 24.dp)
                    ) {
                        item {
                            //                        Youtuber Recommendations
                            Row(
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically,
                                modifier = Modifier.fillMaxWidth()
                            ) {
                                Text(
                                    "YouTubers For You",
                                    style = MaterialTheme.typography.titleMedium,
                                    color = MaterialTheme.colorScheme.primary
                                )
                                IconButton(onClick = {
                                    navController?.navigate(Screen.DETAIL_YOUTUBER_FOR_YOU.name)
                                }) {
                                    Icon(
                                        Icons.Filled.ArrowForward,
                                        contentDescription = "View All",
                                        tint = MaterialTheme.colorScheme.primary
                                    )
                                }
                            }
                            LazyRow(modifier = Modifier.fillMaxWidth()) {
                                items(5) {
                                    CardListYoutuber()
                                    Spacer(modifier = Modifier.width(12.dp))
                                }
                            }

                            Spacer(modifier = Modifier.height(24.dp))

                        }
                        item {
                            Row(
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically,
                                modifier = Modifier.fillMaxWidth()
                            ) {
                                Text(
                                    "Reminders",
                                    style = MaterialTheme.typography.titleMedium,
                                    color = MaterialTheme.colorScheme.primary
                                )
                                IconButton(onClick = {
                                    navController?.navigate(Screen.DETAIL_REMINDER.name)
                                }) {
                                    Icon(
                                        Icons.Filled.ArrowForward,
                                        contentDescription = "View All",
                                        tint = MaterialTheme.colorScheme.primary
                                    )
                                }
                            }
                            Spacer(modifier = Modifier.height(8.dp))
                        }
                        items(5) {
                            TodoItemUi(
                                todoItem = TodoItem(title = "Todo Item ${it + 1}"),
                                onItemClick = { todoItem ->
                                    todoItem.isDone.value = !todoItem.isDone.value
                                })
                            Spacer(modifier = Modifier.height(4.dp))
                        }
                        item {
                            //                        Favorite Youtubers
                            Row(
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically,
                                modifier = Modifier.fillMaxWidth()
                            ) {
                                Text(
                                    "Your FavTubers",
                                    style = MaterialTheme.typography.titleMedium,
                                    color = MaterialTheme.colorScheme.primary
                                )
                                IconButton(onClick = {}) {
                                    Icon(
                                        Icons.Filled.ArrowForward,
                                        contentDescription = "View All",
                                        tint = MaterialTheme.colorScheme.primary
                                    )
                                }
                            }
                            LazyRow(modifier = Modifier.fillMaxWidth()) {
                                items(5) {
                                    CardListYoutuber()
                                    Spacer(modifier = Modifier.width(12.dp))
                                }
                            }

                            Spacer(modifier = Modifier.height(24.dp))
                        }
                        item {
                            Row(
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically,
                                modifier = Modifier.fillMaxWidth()
                            ) {
                                Text(
                                    "Entertainment News",
                                    style = MaterialTheme.typography.titleMedium,
                                    color = MaterialTheme.colorScheme.primary
                                )
                            }
                            Spacer(modifier = Modifier.height(8.dp))
                        }
                        // News
                        items(10) {
                            CardEntertainmentNews()
                            Spacer(modifier = Modifier.height(24.dp))
                        }

                    }
                }
            }

        }
    }

}

@Composable
fun CardDisplayName() {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical =  16.dp, horizontal = 16.dp)
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Image(
                painter = painterResource(id = R.drawable.ic_launcher_foreground),
                contentDescription = "Foto Profil",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(60.dp)
                    .clip(CircleShape),
            )

            Column(modifier = Modifier.padding(horizontal = 0.dp)) {
                Text(
                    "Hi,Zaghy Zalayetha",
                    style = AppTypography.bodyLarge,
                    color = MaterialTheme.colorScheme.onPrimary
                )
                Text(
                    "Welcome back",
                    style = AppTypography.bodySmall,
                    color = MaterialTheme.colorScheme.onPrimary.copy(alpha = 0.5f)
                )
            }
        }
        Image(
            painter = painterResource(id = R.drawable.ic_category_video_games),
            contentDescription = "Foto Profil",
            colorFilter = ColorFilter.tint(color = MaterialTheme.colorScheme.onPrimary),
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .size(32.dp)

        )
    }
}

@Composable
fun CardListYoutuber() {
    Card(
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface,
        ),
    ) {
        Column {
            Image(
                painter = painterResource(id = R.drawable.thumbnail),
                contentDescription = "Thumbnail youtuber",
                modifier = Modifier.size(156.dp)
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text("Jerome Polin", style = AppTypography.titleSmall)
            Spacer(modifier = Modifier.height(8.dp))
            Card(
                colors = CardColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    contentColor = MaterialTheme.colorScheme.onPrimary,
                    disabledContainerColor = MaterialTheme.colorScheme.secondary,
                    disabledContentColor = MaterialTheme.colorScheme.onSecondary
                ), shape = RoundedCornerShape(50.dp)
            ) {
                Text(
                    "Education",
                    style = AppTypography.labelMedium,
                    modifier = Modifier.padding(12.dp)
                )
            }

        }
    }
}

@Composable
fun CardEntertainmentNews() {
    Card(
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface,
        ),
    ) {
        Image(
            painter = painterResource(id = R.drawable.thumbnail_news),
            contentDescription = "Thumbnail News",
            modifier = Modifier
                .fillMaxWidth()
                .height(211.dp),
            contentScale = ContentScale.FillWidth


        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = "Jess dan Sisca Kohl Bucin Masuk Video Populer YouTube Indonesia 2022",
            style = AppTypography.titleMedium
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            "Video kebucinan Jess No Limit bersama Sisca Kohl yang kini resmi menjadi istrinya menjadi salah satu video terpopuler di YouTube Indonesia pada 2022. Dalam video itu, pasangan.",
            style = AppTypography.bodyMedium
        )
    }
}

@Preview
@Composable
fun TodoItemUiPreview() {
    Column(
        modifier = Modifier.padding(MediumDp),
        verticalArrangement = Arrangement.spacedBy(MediumDp)
    ) {
        TodoItemUi(
            todoItem = TodoItem(title = "Todo Item 1"),
            onItemClick = { todoItem -> todoItem.isDone.value = true })
        TodoItemUi(todoItem = TodoItem(title = "Todo Item 2"))
        TodoItemUi(todoItem = TodoItem(title = "Todo Item 3"))
        TodoItemUi(todoItem = TodoItem(title = "Todo Item 4"))
    }
}

@Preview(widthDp = 412, showBackground = true, backgroundColor = 0xFF395790)
@Composable
fun PreviewCardDisplayName() {
    YuKonekTheme {
        CardDisplayName()

    }
}

@Preview
@Composable
fun PreviewCardListYoutuber() {
    YuKonekTheme {
        CardListYoutuber()
    }
}

@Preview
@Composable
fun PreviewCardEntertainmentNews() {
    YuKonekTheme {
        CardEntertainmentNews()
    }
}

@Preview(showSystemUi = true)
@Composable
fun PreviewMainView() {
    YuKonekTheme {
        MainViewHome()
    }
}

@Preview(device = Devices.TABLET)
@Composable
fun PreviewMainViewLandscape() {
    YuKonekTheme {
        MainViewHome()
    }
}

@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun PreviewMainViewDark() {
    YuKonekTheme {
        MainViewHome()
    }
}