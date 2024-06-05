package com.capstone.yukonek.component.reminder

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.capstone.yukonek.R
import com.capstone.yukonek.ui.theme.LargeDp
import com.capstone.yukonek.ui.theme.MediumDp
import com.capstone.yukonek.ui.theme.TodoInputBarFabSize
import com.capstone.yukonek.ui.theme.TodoInputBarHeight
import com.capstone.yukonek.ui.theme.TodoInputBarTextStyle

@Composable
fun TodoInputBar(
    modifier: Modifier = Modifier,
    onAddButtonClick: (String) -> Unit = {}
) {
    // 1. State Management
    val input = remember { mutableStateOf("") }

    Card(
        // 2. Shape Customization
        shape = RoundedCornerShape(size = MediumDp),
        modifier = modifier
            .padding(MediumDp)
            .height(TodoInputBarHeight)
            .fillMaxWidth(),
        // 3. Elevation for Depth
        elevation = CardDefaults.cardElevation(defaultElevation = LargeDp),
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .background(color = MaterialTheme.colorScheme.primary),
            verticalAlignment = Alignment.CenterVertically
        ) {
            TextField(
                modifier = Modifier.weight(1f),
                textStyle = TodoInputBarTextStyle,
                // 4. Data Binding
                value = input.value,
                // 5. Event Handling
                onValueChange = { newText -> input.value = newText },
                placeholder = {
                    Text(
                        text = "Reminder",
                        // 6. Text Styling Depending on TodoItem Status
                        style = TodoInputBarTextStyle.copy(color = MaterialTheme.colorScheme.onPrimary.copy(alpha = 0.5f))
                    )
                },
                singleLine = true,
                colors = TextFieldDefaults.colors(
                    // 7. Custom TextField Appearance
                    focusedContainerColor = Color.Transparent,
                    unfocusedContainerColor = Color.Transparent,
                    cursorColor = Color.White,
                    disabledTextColor = Color.White,
                    focusedTextColor = Color.White,
                    unfocusedTextColor = Color.White,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent
                )
            )
            FloatingActionButton(
                containerColor = MaterialTheme.colorScheme.primary,
                onClick = {
                    // 8. Task Submission Logic
                    if (input.value.isEmpty()) return@FloatingActionButton
                    onAddButtonClick(input.value)
                    input.value = ""
                },
                // 9. FAB Customization
                shape = CircleShape,
                modifier = Modifier.size(TodoInputBarFabSize),
                elevation = FloatingActionButtonDefaults.elevation(
                    defaultElevation = 0.dp,
                    pressedElevation = 0.dp
                )
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_add),
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.onPrimary
                )
            }
            Spacer(modifier = Modifier.width(LargeDp))
        }
    }
}

@Preview
@Composable
fun TodoInputBarPreview() {
    TodoInputBar()
}