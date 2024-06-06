package com.capstone.yukonek.home.data

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import java.util.UUID

data class TodoItem(
    val id: UUID = UUID.randomUUID(),
    val title: String,
    var isDone: MutableState<Boolean> = mutableStateOf(false)
)
