package com.example.todo

import android.app.Application
import androidx.room.Room
import com.example.todo.db.TodoDatabase

class MainApplication: Application() {
    companion object{
        lateinit var todoDatabase: TodoDatabase
    }
    override fun onCreate() {
        super.onCreate()
        todoDatabase=Room.databaseBuilder(
            applicationContext,
            TodoDatabase::class.java,
            TodoDatabase.NAME
        ).build()
    }
}
