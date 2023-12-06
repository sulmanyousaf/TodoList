package com.sulman.todolist.ui.fragment.base.fragments


/*
* this interface is implemented on the basefragment for
* those which fragments wants to override backpress functionality
* */
interface IBackPress {
    fun onBackPressed()
}