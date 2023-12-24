package com.dicoding.todoapp.ui.add

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dicoding.todoapp.data.Task
import com.dicoding.todoapp.data.TaskRepository
import kotlinx.coroutines.launch

class addTaskViewModel(private val taskRepository: TaskRepository) : ViewModel() {

    fun insert(task: Task) {
        viewModelScope.launch {
            taskRepository.insertTask(task)
        }
    }
}