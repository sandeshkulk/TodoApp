package com.example.todo

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class TodoViewModel:ViewModel() {
    private var _todoList = MutableLiveData<List<Todo>>()
    val todoList: LiveData<List<Todo>> = _todoList

    fun getAllTodo() {
       // _todoList.value = TodoManager.getAllTodo()
        //if needed the newest on the first
        _todoList.value = TodoManager.getAllTodo().reversed()
    }

    fun addTodo(title:String){
        if (title.isNotBlank()){
            TodoManager.addTodo(title)
            getAllTodo()
        }
    }

    fun deleteTodo(id: Int){
        TodoManager.deleteTodo(id)
        getAllTodo()
    }
}