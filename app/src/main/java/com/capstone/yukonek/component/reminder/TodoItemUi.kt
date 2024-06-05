package com.capstone.yukonek.component.reminder

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import com.capstone.yukonek.R
import com.capstone.yukonek.home.data.TodoItem
import com.capstone.yukonek.ui.theme.LargeDp
import com.capstone.yukonek.ui.theme.MediumDp
import com.capstone.yukonek.ui.theme.TodoItemActionButtonRippleRadius
import com.capstone.yukonek.ui.theme.TodoItemHeight
import com.capstone.yukonek.ui.theme.TodoItemIconSize
import com.capstone.yukonek.ui.theme.TodoItemTitleTextStyle

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
    val iconColorFilter = if (todoItem.isDone.value) ColorFilter.tint(MaterialTheme.colorScheme.onPrimary.copy(alpha = 0.5f)) else ColorFilter.tint(
        MaterialTheme.colorScheme.onPrimary)
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