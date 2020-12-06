package com.mci.lara.mobile.view.splash

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.mci.lara.mobile.view.BaseViewModel

/**
Lara
Created by Catalin on 11/26/2020
 **/
class SplashViewModel : BaseViewModel() {

    private val canEnter = MutableLiveData<Boolean>()

    init {
        mainHandler.postDelayed({ canEnter.value = true }, 1000)
    }

    fun canEnterApp(): LiveData<Boolean> {
        return canEnter
    }
}