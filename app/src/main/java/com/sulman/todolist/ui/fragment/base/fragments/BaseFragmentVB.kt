package com.sulman.todolist.ui.fragment.base.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding

typealias Inflate<T> = (LayoutInflater, ViewGroup?, Boolean) -> T

/*
* Extend this class if you are creating a fragment
* without ViewModel and don't need back press callback
* */
abstract class BaseFragmentVB<VB : ViewBinding>(
    private val inflate: Inflate<VB>,
) : Fragment() {

    private var _binding: VB? = null
    val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        _binding = inflate(inflater, container, false)
        return _binding!!.root
    }

    private var mIsRestoredFromBackstack: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mIsRestoredFromBackstack = false
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        /*hideBottomNav()*/
        /*hideGenericButton()*/

        /*val tvClass = requireActivity().findViewById<TextView>(R.id.tvClass)
        if (tvClass != null) {
            ScreenDetailsDialog.screenDetails = ScreenDetailsDialog.ScreenDetails(
                packageName = javaClass.`package`.name,
                className = javaClass.simpleName,
                activityName = requireActivity().javaClass.simpleName
            )
        }*/
    }

    fun isRestored() = mIsRestoredFromBackstack

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
        mIsRestoredFromBackstack = true
    }
}