package com.capstone.yukonek.detailreminder

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
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
fun MainViewDetailReminder(
    navController: NavHostController? = null
) {
    val detailReminderViewModel: DetailReminderViewModel = viewModel(
        factory = DetailReminderViewModelFactory.getInstance(
            LocalContext.current
        )
    )
    val todoItems =
        detailReminderViewModel.getAllTodoItems().collectAsState(initial = emptyList())

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
                todoItemsFlow = flowOf(todoItems.value),
                onItemClick = { item ->
                    detailReminderViewModel.updateTodoItems(item.copy(isDone = !item.isDone))
                },
                onItemDelete = { item ->
                    detailReminderViewModel.deleteTodoItems(item)
                },
                overlappingElementsHeight = OverlappingHeight
            )
            TodoInputBar(
                modifier = Modifier.align(Alignment.BottomStart),
                onAddButtonClick = { title ->
                    detailReminderViewModel.insertTodoItems(TodoItem(title = title))
                    Log.d("Reminder", title)
                }
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun MainViewPreview() {
    YuKonekTheme {
        MainViewDetailReminder()
    }
}
