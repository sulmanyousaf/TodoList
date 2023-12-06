package com.sulman.todolist.ui.fragment.base.fragments

import android.os.Bundle
import android.view.View
import androidx.annotation.CallSuper
import androidx.viewbinding.ViewBinding
import com.sulman.todolist.ui.fragment.base.vm.BaseViewModel
import com.sulman.todolist.utils.extensions.registerBackPress

/*
* Extend this fragment if your fragment have viewBinding, viewModel
* and need back press callback
* */
abstract class BaseFragmentVMBP<VB : ViewBinding, VM : BaseViewModel>(
    inflate: Inflate<VB>,
    t: Class<VM>,
) : BaseFragmentVM<VB, VM>(inflate, t), IBackPress {

    @CallSuper
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        registerBackPress(::onBackPressed)
    }
}