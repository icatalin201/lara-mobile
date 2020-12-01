package com.mci.lara.mobile.view

import android.os.Handler
import android.os.HandlerThread
import android.os.Looper
import androidx.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable

/**
Lara
Created by Catalin on 11/26/2020
 **/
open class BaseViewModel : ViewModel() {

    protected val compositeDisposable = CompositeDisposable()
    protected val handler: Handler
    protected val mainHandler = Handler(Looper.getMainLooper())

    init {
        val handlerThread = HandlerThread("BaseViewModelHandlerThread")
        handlerThread.start()
        handler = Handler(handlerThread.looper)
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }

}