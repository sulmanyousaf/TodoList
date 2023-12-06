package com.sulman.todolist.utils.extensions

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LiveData
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.sulman.todolist.R
import kotlinx.coroutines.launch

fun Fragment.registerBackPress(callback: () -> Unit) {
    if (/*isDashboard().not()*/true) {
        requireActivity().onBackPressedDispatcher.addCallback(
            viewLifecycleOwner,
            object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    callback.invoke()
                }
            })
    }
}

//fun Fragment.progressDialog(): AlertDialog {
//    return MaterialAlertDialogBuilder(requireContext()).apply {
//        /*setView(R.layout.loader)*/
//    }.create().apply {
//        setCancelable(false)
//        window?.let {
//            it.setDimAmount(0F)
//            it.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
//        }
//    }
//}

fun Fragment.progressDialog(): AlertDialog {
    return MaterialAlertDialogBuilder(requireContext()).apply {
        setView(R.layout.loading_layout)
    }.create().apply {
        setCancelable(false)
        window?.let {
//            it.setDimAmount(0F)
            it.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            it.setLayout(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
            )
        }
    }
}

fun <T> Fragment.observeLiveData(data: LiveData<T>, observer: (T) -> Unit) {
    data.observe(viewLifecycleOwner, observer)
}

fun Fragment.navigateBack() {
    viewLifecycleOwner.lifecycleScope.launch {
        // Use repeatOnLifecycle to tie the coroutine to the Fragment's lifecycle
        viewLifecycleOwner.lifecycle.repeatOnLifecycle(Lifecycle.State.RESUMED) {
            findNavController().navigateUp()
        }
    }
}