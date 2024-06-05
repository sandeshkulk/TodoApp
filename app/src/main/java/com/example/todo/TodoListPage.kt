package com.example.todo

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import java.text.SimpleDateFormat
import java.util.Locale

@Composable
fun TodoListPage(viewModel: TodoViewModel){
    val todoList by viewModel.todoList.observeAsState()
   // val todoList = getDemoTodo()
    var inputText by remember {
        mutableStateOf("")
    }
    Column (modifier = Modifier
        .fillMaxHeight()
        .padding(10.dp)){
        Row(modifier = Modifier
            .fillMaxWidth()
            .padding(top = 15.dp)
            .padding(10.dp),
            horizontalArrangement = Arrangement.SpaceEvenly)
        {
            OutlinedTextField(value = inputText, 
                onValueChange = {inputText=it},
                placeholder = { Text(text = " Enter New Item Here")})
            Button(onClick = {
                viewModel.addTodo(inputText)
                inputText=""
               // Log.d("Add Button","Clicked")
            }) {
                Text(text = "Add")
            }
        }
        // if not null shows lazy column
        todoList?.let{
            LazyColumn (content = {
                itemsIndexed(it){index:Int, item:Todo->
                    TodoItem(item = item,
                        onDelete = {
                            viewModel.deleteTodo(item.id)
                        }
                    )
                }
            })
        } // if no items found
            ?: Text(text = "No Items Found",
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center,
            fontSize = 15.sp)
    }
}

@Composable
fun TodoItem(item:Todo, onDelete:()->Unit){
    Row(modifier = Modifier
        .fillMaxWidth()
        .padding(10.dp)
        .clip(RoundedCornerShape(35.dp))
        .background(MaterialTheme.colorScheme.tertiary)
        .padding(16.dp), verticalAlignment = Alignment.CenterVertically)
    {
        Column (modifier = Modifier.weight(1.2f))
        {
            Text(text=SimpleDateFormat("HH:mm:aa, dd/mm/yyyy", Locale.ENGLISH).format(item.sessionId),
                fontSize = 10.sp,
                color= Color.LightGray)
          //  Text(text = item.sessionId.toString())
            Text(text = item.title,
                fontSize = 20.sp,
                color = Color.Black)
        }
        IconButton(onClick = onDelete) {
            Icon(painter = painterResource(id = R.drawable.baseline_delete_24),
                contentDescription = "Delete Button",
                tint = Color.White)

        }
    }
}