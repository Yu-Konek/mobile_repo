package com.capstone.yukonek.detailreminder

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.capstone.yukonek.home.data.TodoItem
import com.capstone.yukonek.network.repository.YuKonekRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class DetailReminderViewModel(private val yuKonekRepository: YuKonekRepository) : ViewModel() {
    fun getAllTodoItems(): Flow<List<TodoItem>> = yuKonekRepository.getAllTodoItems()

    fun insertTodoItems(todoItem: TodoItem?) {
        if (todoItem != null) {
            viewModelScope.launch {
                yuKonekRepository.insertTodoItems(todoItem)
            }
        }
    }

    fun deleteTodoItems(todoItem: TodoItem?) {
        if (todoItem != null) {
            viewModelScope.launch {
                yuKonekRepository.deleteTodoItems(todoItem)
            }
        }
    }

    fun updateTodoItems(todoItem: TodoItem?) {
        if (todoItem != null) {
            viewModelScope.launch {
                Log.d("DetailReminderViewModel", "Inserting item: $todoItem")
                yuKonekRepository.updateTodoItems(todoItem)
            }
        }
    }
}
