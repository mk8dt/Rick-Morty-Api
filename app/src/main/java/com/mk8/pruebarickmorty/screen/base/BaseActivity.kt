package com.mk8.pruebarickmorty.screen.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.viewbinding.ViewBinding
import com.mk8.pruebarickmorty.R

abstract class BaseActivity<BINDING : ViewBinding> : AppCompatActivity() {

    protected lateinit var binding: BINDING

    protected val navController by lazy { findNavController(R.id.nav_host) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(initializeBinding().root)
        initView()
    }

    abstract fun initializeBinding(): BINDING

    abstract fun initView()
}