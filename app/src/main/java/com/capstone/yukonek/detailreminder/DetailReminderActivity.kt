package com.capstone.yukonek.detailreminder

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import com.capstone.yukonek.component.appbar.MyTopBar
import com.capstone.yukonek.component.reminder.TodoInputBar
import com.capstone.yukonek.component.reminder.TodoItemsContainer
import com.capstone.yukonek.home.data.TodoItem
import com.capstone.yukonek.ui.theme.OverlappingHeight
import com.capstone.yukonek.ui.theme.YuKonekTheme
import kotlinx.coroutines.flow.flowOf

class DetailReminderActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            YuKonekTheme {
                MainViewDetailReminder()
            }
        }
    }
}

@Composable
fun MainViewDetailReminder(navController: NavHostController? = null) {
    Scaffold(modifier = Modifier.fillMaxSize(), topBar = {
        MyTopBar(title = "Reminders", onBackClick = {
            navController?.popBackStack()
        })
    }) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            TodoItemsContainer(
                // 1. Mock Data for Todo Items
                todoItemsFlow = flowOf(
                    listOf(
                        TodoItem(title = "Todo Item 1"),
                        TodoItem(title = "Todo Item 2"),
                        TodoItem(title = "Todo Item 3"),
                        TodoItem(title = "Todo Item 4"),
                        TodoItem(title = "Todo Item 5"),
                        TodoItem(title = "Todo Item 6"),
                        TodoItem(title = "Todo Item 7"),
                        TodoItem(title = "Todo Item 8"),
                        TodoItem(title = "Todo Item 9"),
                        TodoItem(title = "Todo Item 10"),
                        TodoItem(title = "Todo Item 11"),
                        TodoItem(title = "Todo Item 12"),
                        TodoItem(title = "Todo Item 13"),
                        TodoItem(title = "Todo Item 14"),
                        TodoItem(title = "Todo Item 15")
                    )
                ),
                onItemClick = { item ->
                    item.isDone.value = !item.isDone.value
                },
                onItemDelete = {},
                // 2. Space Adjustment for Overlapping UI Elements
                overlappingElementsHeight = OverlappingHeight
            )
            TodoInputBar(
                modifier = Modifier.align(Alignment.BottomStart),
                onAddButtonClick = {}
            )
        }
    }
}

@Preview()
@Composable
private fun MainViewPreview() {
    YuKonekTheme {
        MainViewDetailReminder()
    }
}
