package com.mci.lara.mobile.view.splash

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.mci.lara.mobile.data.repository.TokenRepository
import com.mci.lara.mobile.view.BaseViewModel

/**
Lara
Created by Catalin on 11/26/2020
 **/
class SplashViewModel(
    private val tokenRepository: TokenRepository
) : BaseViewModel() {

    private val loggedIn = MutableLiveData<Boolean>()

    init {
//        loggedIn.value = tokenRepository.isLogged()
        loggedIn.value = true
    }

    fun isLoggedIn(): LiveData<Boolean> {
        return loggedIn
    }
}