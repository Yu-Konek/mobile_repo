package com.capstone.yukonek.home


import android.content.res.Configuration
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.capstone.yukonek.R
import com.capstone.yukonek.component.card.CardDisplayName
import com.capstone.yukonek.component.card.CardEntertainmentNews
import com.capstone.yukonek.component.card.CardListYoutuberColumn
import com.capstone.yukonek.component.reminder.TodoItemUi
import com.capstone.yukonek.component.reminder.TodoItemsContainer
import com.capstone.yukonek.detailreminder.DetailReminderViewModel
import com.capstone.yukonek.detailreminder.DetailReminderViewModelFactory
import com.capstone.yukonek.home.data.Channel
import com.capstone.yukonek.home.data.MResponseNews
import com.capstone.yukonek.home.data.TodoItem
import com.capstone.yukonek.home.data.channels
import com.capstone.yukonek.mainscreen.MainViewModelFactory
import com.capstone.yukonek.mainscreen.MainViewmodel
import com.capstone.yukonek.navigations.Screen
import com.capstone.yukonek.network.Result
import com.capstone.yukonek.ui.theme.MediumDp
import com.capstone.yukonek.ui.theme.OverlappingHeight
import com.capstone.yukonek.ui.theme.YuKonekTheme
import kotlinx.coroutines.flow.flowOf
import java.net.URLEncoder
import java.nio.charset.StandardCharsets

class HomeActivity : ComponentActivity() {
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

    val viewModel: MainViewmodel = viewModel(factory = MainViewModelFactory(LocalContext.current))
    val entertainmentNews by viewModel.getTopHeadlineEntertainement().observeAsState()
    val reminderViewModel: DetailReminderViewModel = viewModel(
        factory = DetailReminderViewModelFactory.getInstance(
            LocalContext.current
        )
    )
    val todoItems = reminderViewModel.getAllTodoItems()
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.primary)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()

        ) {
            Column(modifier = Modifier.padding(top = 24.dp)) {
                CardDisplayName()
            }

            Box(
                modifier = Modifier
                    .fillMaxSize()
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
                                    Icons.AutoMirrored.Filled.ArrowForward,
                                    contentDescription = "View All",
                                    tint = MaterialTheme.colorScheme.primary
                                )
                            }
                        }
                        LazyRow(modifier = Modifier.fillMaxWidth()) {
                            items(channels.size-5){
                                CardListYoutuberColumn(onClick = { navController?.navigate("${Screen.DETAIL_YOUTUBER.name}/${channels[it].id}") },
                                    name = channels[it].name,
                                    thumbnail = channels[it].thumbnail,
                                    subscribers = channels[it].subscriber,
                                    category = channels[it].category)
                                Spacer(modifier = Modifier.width(12.dp))
                            }
                        }

                        Spacer(modifier = Modifier.height(24.dp))

                    }
//                    item {
//                        Row(
//                            horizontalArrangement = Arrangement.SpaceBetween,
//                            verticalAlignment = Alignment.CenterVertically,
//                            modifier = Modifier.fillMaxWidth()
//                        ) {
//                            Text(
//                                "Reminders",
//                                style = MaterialTheme.typography.titleMedium,
//                                color = MaterialTheme.colorScheme.primary
//                            )
//                            IconButton(onClick = {
//                                navController?.navigate(Screen.DETAIL_REMINDER.name)
//                            }) {
//                                Icon(
//                                    Icons.AutoMirrored.Filled.ArrowForward,
//                                    contentDescription = "View All",
//                                    tint = MaterialTheme.colorScheme.primary
//                                )
//                            }
//                        }
//                        Spacer(modifier = Modifier.height(8.dp))
//                    }
//                    item {
//                        TodoItemUi(TodoItem(title = todoItems. toString()))
//                        Log.d("ini apa", todoItems.toString())
//                        TodoItemsContainer(
//                            todoItemsFlow = flowOf(todoItems.value),
//                            onItemClick = { item ->
//                                reminderViewModel.updateTodoItems(item.copy(isDone = !item.isDone))
//                            },
//                            onItemDelete = { item ->
//                                reminderViewModel.deleteTodoItems(item)
//                            },
//                            overlappingElementsHeight = OverlappingHeight
//                        )
//                        Spacer(modifier = Modifier.height(4.dp))
//                    }
//                    item {
//                        //                        Favorite Youtubers
//                        Row(
//                            horizontalArrangement = Arrangement.SpaceBetween,
//                            verticalAlignment = Alignment.CenterVertically,
//                            modifier = Modifier.fillMaxWidth()
//                        ) {
//                            Text(
//                                "Your FavTubers",
//                                style = MaterialTheme.typography.titleMedium,
//                                color = MaterialTheme.colorScheme.primary
//                            )
//                            IconButton(onClick = {
//                                navController?.navigate(Screen.DETAIL_FAVORITE_YOUTUBER.name)
//                            }) {
//                                Icon(
//                                    Icons.AutoMirrored.Filled.ArrowForward,
//                                    contentDescription = "View All",
//                                    tint = MaterialTheme.colorScheme.primary
//                                )
//                            }
//                        }
//                        LazyRow(modifier = Modifier.fillMaxWidth()) {
//                            items(5) {
//                                CardListYoutuberColumn(onClick = { navController?.navigate(Screen.DETAIL_YOUTUBER.name) })
//                                Spacer(modifier = Modifier.width(12.dp))
//                            }
//                        }
//
//                        Spacer(modifier = Modifier.height(24.dp))
//                    }
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
                    when (entertainmentNews) {
                        is Result.Loading -> {
                            item {
                                CircularProgressIndicator()
                            }
                        }

                        is Result.Success -> {
                            (entertainmentNews as Result.Success<MResponseNews>).data.data?.posts.let { articles ->
                                articles?.take(5)?.size?.let {
                                    items(it) { index ->
                                        CardEntertainmentNews(
                                            title = articles[index]?.title ?: "-",
                                            description = articles[index]?.description ?: "-",
                                            image = articles[index]?.thumbnail ?: "",
                                            onClick = {
                                                Log.e(
                                                    "HomeActivity",
                                                    "MainViewHome: ${articles[index]?.link}"
                                                )
                                                navController?.navigate(
                                                    "${Screen.DETAIL_NEWS.name}/${
                                                        URLEncoder.encode(
                                                            articles[index]?.link,
                                                            StandardCharsets.UTF_8.toString()
                                                        )
                                                    }"
                                                )
                                            })
                                        Spacer(modifier = Modifier.height(24.dp))
                                    }
                                }
                            }
                        }

                        is Result.Error -> {
                            item {
                                CardEntertainmentNews(
                                    title = "-",
                                    description = "-",
                                    image = "",
                                    onClick = {
                                        navController?.navigate(
                                            "${Screen.DETAIL_NEWS.name}/${
                                                URLEncoder.encode(
                                                    "",
                                                    StandardCharsets.UTF_8.toString()
                                                )
                                            }"
                                        )
                                    })
                                Spacer(modifier = Modifier.height(24.dp))
                            }
                        }

                        else -> {
//                            data null
                        }
                    }


                }
            }
        }

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
            todoItem = TodoItem(
                title = "Todo Item 1"),
            onItemClick = { todoItem -> todoItem.isDone = true })
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

//@Preview
//@Composable
//fun PreviewCardListYoutuber() {
//    YuKonekTheme {
//        CardListYoutuberColumn()
//    }
//}


@Preview()
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