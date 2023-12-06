package com.sulman.todolist.data.repo

import com.sulman.todolist.data.api.domain.TodoDao
import com.sulman.todolist.data.api.helper.Result
import com.sulman.todolist.data.api.helper.safeApiCall
import com.sulman.todolist.data.api.model.response.TodoResponse
import com.sulman.todolist.data.api.model.response.TodoResponse.TodoModel
import com.sulman.todolist.data.api.service.TodoService
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.withContext
import javax.inject.Inject

class TodoRepo @Inject constructor(
    private val todoDao: TodoDao,
    private val service: TodoService,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
) {

    fun onlineData(): Flow<Result<TodoResponse>> {
        return safeApiCall { service.onlineData() }
    }

    suspend fun insert(task: TodoModel) {
        withContext(ioDispatcher) {
            todoDao.insertTask(task)
        }
    }

    suspend fun insertOrUpdateTodos(listOfTask: List<TodoModel>) {
        withContext(ioDispatcher) {
            todoDao.insertOrUpdateTodos(listOfTask)
        }
    }

    suspend fun update(task: TodoModel) {
        withContext(ioDispatcher) {
            todoDao.update(task)
        }
    }
    suspend fun delete(task: TodoModel) {
        withContext(ioDispatcher) {
            todoDao.delete(task)
        }
    }
}



