package com.mk8.pruebarickmorty.screen.base

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.*
import org.koin.core.KoinComponent
import kotlin.coroutines.CoroutineContext

open class BaseViewModel : ViewModel(), KoinComponent {

    private lateinit var job: Job
    protected lateinit var uiScope: CoroutineScope
    protected lateinit var ioContext: CoroutineContext

    fun init() {
        onCreate()
    }

    protected open fun onCreate() {
        job = SupervisorJob()
        uiScope = CoroutineScope(Dispatchers.Main + job)
        ioContext = Dispatchers.IO + job
    }

    override fun onCleared() {
        super.onCleared()
        uiScope.cancel()
        ioContext.cancel()
    }
}