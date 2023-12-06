package com.sulman.todolist.ui.fragment.base.vm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow

open class BaseViewModel : ViewModel() {

    private val _progress = MutableLiveData<Boolean>()
    val progress: LiveData<Boolean> = _progress

    private val baseEventsChannel = Channel<BaseEvent>()
    val baseEvents = baseEventsChannel.receiveAsFlow()

    fun showLoader() {
        _progress.postValue(true)
    }

    fun hideLoader() {
        _progress.postValue(false)
    }

    suspend fun sendError(error: String?) {
        hideLoader()
        baseEventsChannel.send(BaseEvent.ShowErrorMessage(error.orEmpty()))
    }

    sealed class BaseEvent {
        data class ShowErrorMessage(val msg: String) : BaseEvent()
        /*data class ShowSuccessMessage(val msg: String) : BaseEvent()*/
    }
}