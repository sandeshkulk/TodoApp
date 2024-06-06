package com.example.todo

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.time.Instant
import java.util.Date

class TodoViewModel:ViewModel() {
    val todoDao=MainApplication.todoDatabase.getTodoDao()
    val todoList: LiveData<List<Todo>> = todoDao.getAllTodo()
    fun addTodo(title:String){
        viewModelScope.launch (Dispatchers.IO){
            todoDao.addTodo(Todo(title=title, sessionId = Date.from(Instant.now())))
        }
    }
    fun deleteTodo(id: Int){
        viewModelScope.launch (Dispatchers.IO) {
            todoDao.deleteTodo(id)
        }
    }
}