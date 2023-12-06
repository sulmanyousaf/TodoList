package com.sulman.todolist.data.api.model.response

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize



data class TodoResponse(
    val data: List<TodoModel>
){
    @Parcelize
    @Entity(tableName = "task_table")
    data class TodoModel(
        @PrimaryKey(autoGenerate = false)
        val id : Int = 0,
        @ColumnInfo(name = "title")
        val title : String,
        @ColumnInfo(name = "description")
        val description : String,
        @ColumnInfo(name = "creationDate")
        val creationDate : String
    ) : Parcelable
}

