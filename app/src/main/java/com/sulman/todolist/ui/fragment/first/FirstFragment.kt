package com.sulman.todolist.ui.fragment.first

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.google.gson.Gson
import com.sulman.todolist.data.api.model.response.TodoResponse.TodoModel
import com.sulman.todolist.databinding.FragmentFirstBinding
import com.sulman.todolist.ui.fragment.base.fragments.BaseFragmentVMBP
import com.sulman.todolist.utils.extensions.navigateBack
import com.sulman.todolist.utils.extensions.showToast
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class FirstFragment : BaseFragmentVMBP<FragmentFirstBinding, FirstFragmentVM>(
    FragmentFirstBinding::inflate,
    FirstFragmentVM::class.java
) {
    private lateinit var todoItem: TodoModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val jsonData = arguments?.getString("stringData")
        binding.apply {

            if (jsonData != null){
                Gson().fromJson(jsonData, TodoModel::class.java).let {
                    btnAddTodo.text = "Update Todo"
                    etTitle.setText(it.title)
                    etTodoDes.setText(it.description)
                    todoItem = it
                }
            } else {
                btnAddTodo.text = "Add Todo"
                etTitle.text?.clear()
                etTodoDes.text?.clear()
            }

            btnAddTodo.setOnClickListener {
                viewModel.showLoader()

//                viewLifecycleOwner.lifecycleScope.launch {
//                    viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                        if (btnAddTodo.text == "Add Todo") {
                            val student = TodoModel(title = etTitle.text.toString(), description = etTodoDes.text.toString(), creationDate = "20")
                            viewModel.insert(student)
                            showToast("Data Entered Successfully.")
                        } else {
                            todoItem = TodoModel(id = todoItem.id,title = etTitle.text.toString(), description = etTodoDes.text.toString(), creationDate = "20")
                            viewModel.update(todoItem)
                            showToast("Data Updated Successfully.")
                        }
                        etTitle.text?.clear()
                        etTodoDes.text?.clear()
                        viewModel.hideLoader()
                        onBackPressed()
//                    }
//                }
            }
        }
    }

    override fun onBackPressed() {
        navigateBack()
    }


}