package com.capstone.yukonek.component.reminder

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.capstone.yukonek.home.data.TodoItem
import com.capstone.yukonek.ui.theme.MediumDp
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

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
fun TodoItemsContainerPreview() {
    Scaffold { innerPadding ->
        TodoItemsContainer(
            modifier = Modifier.padding(innerPadding),
            todoItemsFlow = flowOf(
                listOf(
                    TodoItem(title = "Todo Item 1"),
                    TodoItem(title = "Todo Item 2"),
                    TodoItem(title = "Todo Item 3"),
                    TodoItem(title = "Todo Item 4"),
                )
            )
        )
    }

}