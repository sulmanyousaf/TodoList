package com.sulman.todolist.ui.fragment.second

import android.content.Context
import android.net.ConnectivityManager
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.Gson
import com.sulman.todolist.R
import com.sulman.todolist.databinding.FragmentSecondBinding
import com.sulman.todolist.ui.fragment.base.fragments.BaseFragmentVMBP
import com.sulman.todolist.ui.fragment.base.vm.BaseViewModel
import com.sulman.todolist.utils.extensions.navigateBack
import com.sulman.todolist.utils.extensions.showToast
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SecondFragment : BaseFragmentVMBP<FragmentSecondBinding, SecondFragmentVM>(
    FragmentSecondBinding::inflate,
    SecondFragmentVM::class.java
) {

//    override fun onCreateView(
//        inflater: LayoutInflater,
//        container: ViewGroup?,
//        savedInstanceState: Bundle?
//    ): View {
//        // Inflate the layout for this fragment
//        val view = inflater.inflate(R.layout.fragment_second, container, false)
//
//        val toolbar = view.findViewById<MaterialToolbar>(R.id.toolbar)
//        toolbar?.let {
//            (requireActivity() as AppCompatActivity).setSupportActionBar(it)
//        }
//
////        setHasOptionsMenu(true)
//
//        return view
//    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply {
//            toolbar.let {
//                (requireActivity() as AppCompatActivity).setSupportActionBar(it)
//                setHasOptionsMenu(true)
//            }
            binding.fab.setOnClickListener { view ->
                val bundle = Bundle()
                bundle.putString("stringData", "Add Todo")
                findNavController().navigate(R.id.Second_to_First)
            }
        }

        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.allTasks.collect { tasks ->
                    Log.e("ApiResponse", "response tasks:${tasks}")
                    // Update the RecyclerView with the list of students
                    binding.apply {
                        recyclerView.apply {
                            adapter = SecondFragmentAdapter(tasks,{
                                val bundle = Bundle()
                                bundle.putString("stringData", Gson().toJson(it))
                                findNavController().navigate(R.id.action_SecondFragment_to_FirstFragment, bundle)
                            }) {
                                viewModel.delete(it)
                            }
                        }
                    }
                }
            }

            launch {
                viewModel.baseEvents.collect { event ->
                    when (event) {
                        is BaseViewModel.BaseEvent.ShowErrorMessage -> showToast(event.msg)
                    }
                }
            }
        }
    }

//    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
//        inflater.inflate(R.menu.menu_main, menu)
//    }
//
//    override fun onOptionsItemSelected(item: MenuItem): Boolean {
//        return when (item.itemId) {
//            R.id.online_mode -> {
//                // Handle online mode click
//                showToast("Online Mode Clicked")
//                true
//            }
//
//            R.id.offline_mode -> {
//                // Handle backup click
//                showToast("Offline Mode Clicked")
//                true
//            }
//
//            R.id.backup_button -> {
//                // Handle backup click
//                showToast("Backup Clicked")
//                true
//            }
//
//            else -> false
//        }
//    }
//
//    private fun isNetworkAvailable(): Boolean {
//        val connectivityManager =
//            requireActivity().getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager?
//        val networkInfo = connectivityManager?.activeNetworkInfo
//        return networkInfo?.isConnectedOrConnecting == true
//    }

    override fun onBackPressed() {
        navigateBack()
    }
}