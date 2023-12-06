package com.sulman.todolist.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.sulman.todolist.data.api.domain.TodoDao
import com.sulman.todolist.data.api.model.response.TodoResponse.TodoModel

@Database(entities = [TodoModel::class], version = 1, exportSchema = false)
abstract class TodoDatabase : RoomDatabase() {
    abstract fun todoDao(): TodoDao
}