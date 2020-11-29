package com.mci.lara.mobile.view.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.mci.lara.mobile.data.repository.TokenRepository
import com.mci.lara.mobile.data.repository.UserRepository
import com.mci.lara.mobile.view.BaseViewModel

/**
Lara
Created by Catalin on 11/25/2020
 **/
class LoginViewModel(
    private val userRepository: UserRepository,
    private val tokenRepository: TokenRepository
) : BaseViewModel() {

    private val loading = MutableLiveData<Boolean>()
    private val success = MutableLiveData<Boolean>()

    var username = ""
    var password = ""

    fun isLoading(): LiveData<Boolean> {
        return loading
    }

    fun isSuccess(): LiveData<Boolean> {
        return success
    }

    fun login() {
        loading.value = true
        val disposable = userRepository
            .login(username, password)
            .subscribe(
                {
                    tokenRepository.save(it.accessToken, it.expiresIn)
                    loading.value = false
                    success.value = true
                },
                {
                    it.printStackTrace()
                    loading.value = false
                    success.value = false
                }
            )
        compositeDisposable.add(disposable)
    }

}