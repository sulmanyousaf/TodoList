package com.sulman.todolist.data.api.domain

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.sulman.todolist.data.api.helper.ApiNames
import com.sulman.todolist.data.api.model.response.TodoResponse.TodoModel
import kotlinx.coroutines.flow.Flow
import retrofit2.http.GET

@Dao
interface TodoDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTask(todo: TodoModel)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertOrUpdateTodos(todos: List<TodoModel>)

    @Update
    suspend fun update(todo: TodoModel)

    @Delete
    suspend fun delete(todo: TodoModel)

    @Query("SELECT * FROM task_table")
    fun getAllTask(): Flow<List<TodoModel>>
}