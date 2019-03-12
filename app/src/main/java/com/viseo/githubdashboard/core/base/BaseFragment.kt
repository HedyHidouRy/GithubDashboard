package com.viseo.githubdashboard.core.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel


abstract class BaseFragment: Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        val dataBinding: ViewDataBinding = DataBindingUtil.inflate(inflater, layoutId(), container, false)
        dataBinding.setVariable(bindingVariable(), viewModelBinding())
        dataBinding.lifecycleOwner = this
        dataBinding.executePendingBindings()
        return dataBinding.root
    }

    abstract fun layoutId(): Int
    abstract fun viewModelBinding(): ViewModel
    abstract fun bindingVariable(): Int
}