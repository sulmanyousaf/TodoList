package com.sulman.todolist.ui.fragment.second

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.sulman.todolist.data.api.domain.TodoDao
import com.sulman.todolist.data.api.helper.Result
import com.sulman.todolist.data.api.model.response.TodoResponse.TodoModel
import com.sulman.todolist.data.repo.TodoRepo
import com.sulman.todolist.ui.fragment.base.vm.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SecondFragmentVM @Inject constructor(
    private val todoDao: TodoDao,
    private val repository: TodoRepo
) : BaseViewModel() {

    private val _allTasks = MutableStateFlow<List<TodoModel>>(emptyList())
    val allTasks = _allTasks.asStateFlow()

    init {
        onlineData()
    }

    init {
        observeDataFromDao()
    }

    fun delete(task: TodoModel) {
        viewModelScope.launch {
            repository.delete(task)
        }
    }

    private fun observeDataFromDao() {
        showLoader()
        viewModelScope.launch {
            todoDao.getAllTask().collect { tasks ->
                _allTasks.value = tasks
                hideLoader()
            }
        }
    }

    fun onlineData() {
        showLoader()
        Log.e("ApiResponse", "online")
        repository.onlineData().onEach { result ->
            when (result) {
                is Result.Error -> {
                    hideLoader()
                    sendError(result.message)
                    Log.e("ApiResponse", "products Error ${result.message}")
                }

                is Result.Success -> {
                    hideLoader()
                    repository.insertOrUpdateTodos(result.data.data)
                    Log.e("ApiResponse", "response data:${result.data.data}")
                }
            }
        }.launchIn(viewModelScope)
    }
}