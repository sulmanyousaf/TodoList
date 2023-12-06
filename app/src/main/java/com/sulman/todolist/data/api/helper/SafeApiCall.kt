package com.sulman.todolist.data.api.helper

import android.util.Log
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import retrofit2.HttpException
import java.io.IOException

inline fun <T> safeApiCall(crossinline apiCall: suspend () -> T): Flow<Result<T>> {
    return flow {
        try {
            val response = apiCall()
            Log.e("ApiResponse", "products safeApiCall")
            emit(Result.Success(response))
        } catch (e: Exception) {
            emit(Result.Error(mapError(e)))
        }
    }.flowOn(Dispatchers.IO)
}

fun mapError(throwable: Throwable): String {
    return when (throwable) {
        is HttpException -> "HTTP Error ${throwable.code()}"
        is IOException -> "Network Error"
        else -> "Unknown Error"
    }
}