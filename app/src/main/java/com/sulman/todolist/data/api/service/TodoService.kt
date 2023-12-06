package com.sulman.todolist.data.api.service

import com.sulman.todolist.data.api.helper.ApiNames
import com.sulman.todolist.data.api.model.response.TodoResponse
import retrofit2.http.GET

interface TodoService {
    @GET(ApiNames.GET_DATA)
    suspend fun onlineData(): TodoResponse
}