package com.sulman.todolist.utils.extensions

import androidx.appcompat.app.AlertDialog

fun AlertDialog.visibility(visible: Boolean) {
    if (visible) show() else dismiss()
}