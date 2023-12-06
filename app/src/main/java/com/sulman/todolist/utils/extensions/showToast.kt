package com.sulman.todolist.utils.extensions

import android.content.Context
import android.widget.Toast
import androidx.fragment.app.Fragment

fun Fragment.showToast(message: CharSequence, duration: Int = Toast.LENGTH_SHORT) {
    context?.showToast(message, duration)
}

fun Fragment.showToast(messageResId: Int, duration: Int = Toast.LENGTH_SHORT) {
    context?.showToast(messageResId, duration)
}

fun Context?.showToast(message: CharSequence, duration: Int = Toast.LENGTH_SHORT) {
    this?.let {
        Toast.makeText(it, message, duration).show()
    }
}

fun Context?.showToast(messageResId: Int, duration: Int = Toast.LENGTH_SHORT) {
    this?.let {
        Toast.makeText(it, it.getString(messageResId), duration).show()
    }
}
