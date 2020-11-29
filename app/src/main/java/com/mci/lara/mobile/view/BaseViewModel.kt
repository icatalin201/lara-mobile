package com.mci.lara.mobile.view

import androidx.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable

/**
Lara
Created by Catalin on 11/26/2020
 **/
open class BaseViewModel : ViewModel() {

    protected val compositeDisposable = CompositeDisposable()

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }

}