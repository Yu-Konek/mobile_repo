package com.capstone.yukonek.home

import android.content.res.Configuration
import android.os.Bundle
import android.widget.Space
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.capstone.yukonek.R
import com.capstone.yukonek.home.data.TodoItem
import com.capstone.yukonek.ui.theme.LargeDp
import com.capstone.yukonek.ui.theme.MediumDp
import com.capstone.yukonek.ui.theme.OverlappingHeight
import com.capstone.yukonek.ui.theme.TodoItemActionButtonRippleRadius
import com.capstone.yukonek.ui.theme.TodoItemHeight
import com.capstone.yukonek.ui.theme.TodoItemIconSize
import com.capstone.yukonek.ui.theme.TodoItemTitleTextStyle
import com.example.compose.ColorFamily
import com.example.compose.TodoItemBackgroundColor
import com.example.compose.TodoItemIconColor
import com.example.compose.TodoItemTextColor
import com.example.compose.YuKonekTheme
import com.example.compose.onPrimaryLight
import com.example.compose.primaryLight
import com.example.ui.theme.AppTypography
import com.google.android.material.color.MaterialColors
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

class Home : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            YuKonekTheme {
                MainView()
            }
        }
    }
}

@Composable
fun MainView() {
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
                CardDisplayName()
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
                                    "Reminders",
                                    style = MaterialTheme.typography.titleMedium,
                                    color = MaterialTheme.colorScheme.primary
                                )
                            }
                            Spacer(modifier = Modifier.height(8.dp))
                        }
                        items(5){
                            TodoItemUi(todoItem = TodoItem(title = "Todo Item ${it+1}"), onItemClick = {todoItem -> todoItem.isDone.value = !todoItem.isDone.value })
                            Spacer(modifier = Modifier.height(4.dp))
                        }
                        item{
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
                        item{
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
                        items(10){
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
            .padding(vertical = 24.dp, horizontal = 16.dp)
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
    Card(colors = CardColors(
        containerColor = MaterialTheme.colorScheme.surface,
        contentColor = MaterialTheme.colorScheme.primary,
        disabledContainerColor = MaterialTheme.colorScheme.surface,
        disabledContentColor = MaterialTheme.colorScheme.secondary
    )) {
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


@Composable
fun TodoItemUi(
    todoItem: TodoItem = TodoItem(title = "Todo Item"),
    //  1. Lambda Function Parameters for Flexibility
    onItemClick: (TodoItem) -> Unit = {},
    onItemDelete: (TodoItem) -> Unit = {}
) {
    // 2. Adaptive Color Scheme
    val backgroundColor = if (todoItem.isDone.value) MaterialTheme.colorScheme.primary.copy(alpha = 0.5f) else MaterialTheme.colorScheme.primary
    val textColor = if (todoItem.isDone.value) MaterialTheme.colorScheme.onPrimary.copy(alpha = 0.5f) else MaterialTheme.colorScheme.onPrimary

    // 3. Text Decoration
    val textDecoration = if (todoItem.isDone.value) TextDecoration.LineThrough else null

    // 4. Dynamic Icons
    val iconId = if (todoItem.isDone.value) R.drawable.ic_selected_checkbox else R.drawable.ic_empty_checkbox
    val iconColorFilter = if (todoItem.isDone.value) ColorFilter.tint(MaterialTheme.colorScheme.onPrimary.copy(alpha = 0.5f)) else ColorFilter.tint(MaterialTheme.colorScheme.onPrimary)
    val iconTintColor = if (todoItem.isDone.value) MaterialTheme.colorScheme.onPrimary.copy(alpha = 0.5f) else MaterialTheme.colorScheme.onPrimary

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(TodoItemHeight),
        elevation = CardDefaults.cardElevation(defaultElevation = LargeDp),
        shape = RoundedCornerShape(size = MediumDp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .background(backgroundColor)
                // 5. Clickable Modifier with Ripple Effect:
                .clickable(
                    interactionSource = remember { MutableInteractionSource() },
                    indication = rememberRipple(bounded = true)
                ) { onItemClick(todoItem) },
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Image(
                painter = painterResource(id = iconId),
                contentDescription = null,
                modifier = Modifier
                    .padding(MediumDp)
                    .size(TodoItemIconSize),
                colorFilter = iconColorFilter
            )
            Text(
                text = todoItem.title,
                modifier = Modifier.weight(1f),
                style = TodoItemTitleTextStyle.copy(color = textColor),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                textDecoration = textDecoration
            )
            // 6. IconButton for Deletion
            IconButton(
                onClick = { onItemDelete(todoItem) },
                modifier = Modifier.size(TodoItemActionButtonRippleRadius)
            ) {
                Icon(
                    modifier = Modifier.size(TodoItemIconSize),
                    painter = painterResource(id = R.drawable.ic_delete),
                    contentDescription = null,
                    tint = iconTintColor
                )
            }
        }
    }
}

@Composable
fun TodoItemsContainer(
    modifier: Modifier = Modifier,
    todoItemsFlow: Flow<List<TodoItem>> = flowOf(listOf()),
    onItemClick: (TodoItem) -> Unit = {},
    onItemDelete: (TodoItem) -> Unit = {},
    overlappingElementsHeight: Dp = 0.dp
) {
    // 1. Flow Data Collection
    val todos = todoItemsFlow.collectAsState(initial = listOf()).value
    // 2. LazyColumn Setup
    LazyColumn(
        modifier = modifier.fillMaxSize(),
        contentPadding = PaddingValues(MediumDp),
        verticalArrangement = Arrangement.spacedBy(MediumDp)
    ) {
        // 3. Items Rendering
        items(todos, key = { it.id }) { item ->
            TodoItemUi(
                todoItem = item,
                onItemClick = onItemClick,
                onItemDelete = onItemDelete
            )
        }
        // 4. Layout Adjustment
        item { Spacer(modifier = Modifier.height(overlappingElementsHeight)) }
    }
}

@Preview
@Composable
fun TodoItemUiPreview() {
    Column(
        modifier = Modifier.padding(MediumDp),
        verticalArrangement = Arrangement.spacedBy(MediumDp)
    ) {
        TodoItemUi(todoItem = TodoItem(title = "Todo Item 1"), onItemClick = {todoItem -> todoItem.isDone.value = true })
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

@Preview
@Composable
fun PreviewMainView() {
    YuKonekTheme {
        MainView()
    }
}

@Preview(device = Devices.TABLET)
@Composable
fun PreviewMainViewLandscape() {
    YuKonekTheme {
        MainView()
    }
}

@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun PreviewMainViewDark() {
    YuKonekTheme {
        MainView()
    }
}