package com.sulman.todolist.ui.fragment.base.fragments

import android.os.Bundle
import android.view.View
import androidx.annotation.CallSuper
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavDirections
import androidx.navigation.fragment.findNavController
import androidx.viewbinding.ViewBinding
import com.sulman.todolist.ui.fragment.base.vm.BaseViewModel
import com.sulman.todolist.utils.extensions.observeLiveData
import com.sulman.todolist.utils.extensions.progressDialog
import com.sulman.todolist.utils.extensions.visibility

/*
* VB stands for ViewBinding
* VM stands for ViewModel
* BaseFragmentVM means this fragment will be extended by those fragment
* which are having viewModels. For a fragment without viewModel see
* {@link BaseFragmentVB}
* */

abstract class BaseFragmentVM<VB : ViewBinding, VM : BaseViewModel>(
    inflate: Inflate<VB>,
    private val t: Class<VM>,
) : BaseFragmentVB<VB>(inflate) {

    private lateinit var progress: AlertDialog
    lateinit var viewModel: VM

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel = ViewModelProvider(this).get(t)
        /*hideGenericButton()*/
    }

    @CallSuper
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        progress = progressDialog()

        observeLiveData(viewModel.progress) {
            progress.visibility(it)
        }
    }

    fun baseNavigateTo(direction: NavDirections) {
        findNavController().navigate(direction)
    }
}