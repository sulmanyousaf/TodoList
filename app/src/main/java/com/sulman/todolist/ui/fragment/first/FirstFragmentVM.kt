package com.sulman.todolist.ui.fragment.first

import androidx.lifecycle.viewModelScope
import com.sulman.todolist.data.api.model.response.TodoResponse.TodoModel
import com.sulman.todolist.data.repo.TodoRepo
import com.sulman.todolist.ui.fragment.base.vm.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FirstFragmentVM @Inject constructor(private val repository: TodoRepo) : BaseViewModel() {

    fun insert(task: TodoModel) {
        viewModelScope.launch {
            repository.insert(task)
        }
    }
    fun update(task: TodoModel) {
        viewModelScope.launch {
            repository.update(task)
        }
    }
}

