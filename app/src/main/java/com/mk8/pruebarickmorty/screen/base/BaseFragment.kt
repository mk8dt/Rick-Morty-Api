package com.mk8.pruebarickmorty.screen.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.viewbinding.ViewBinding
import com.mk8.pruebarickmorty.screen.MainActivity
import com.mk8.pruebarickmorty.R
import com.mk8.pruebarickmorty.config.extension.gone
import com.mk8.pruebarickmorty.config.extension.visible

abstract class BaseFragment<BINDING : ViewBinding> : Fragment() {

    protected lateinit var binding: BINDING

    protected val navController by lazy { requireActivity().findNavController(R.id.nav_host) }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? = initializeBinding().root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }

    abstract fun initializeBinding(): BINDING

    abstract fun initView()

    private fun mainActivity() = (activity as MainActivity)

    protected fun showProgressBar() {
        mainActivity().progressBar().visible()
    }

    protected fun hideProgressBar() {
        mainActivity().progressBar().gone()
    }

    protected fun showToast(message: String) {
        Toast.makeText(context, message, Toast.LENGTH_LONG).show()
    }
}