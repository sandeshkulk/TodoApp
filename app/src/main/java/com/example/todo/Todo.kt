package com.example.todo

import java.time.Instant
import java.util.Date

data class Todo(
    var id: Int,
    var title: String,
    var sessionId: Date
)

fun getDemoTodo():List<Todo>{
    return listOf<Todo>(
        Todo(1,"First Item", Date.from(Instant.now())),
        Todo(2,"Second Item", Date.from(Instant.now())),
        Todo(3,"Third Item", Date.from(Instant.now())),
        Todo(4,"Fourth Item", Date.from(Instant.now()))
    )
}