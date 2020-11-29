package com.mci.lara.mobile.view.register

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.mci.lara.mobile.data.repository.UserRepository
import com.mci.lara.mobile.view.BaseViewModel

/**
Lara
Created by Catalin on 11/26/2020
 **/
class RegisterViewModel(
    private val userRepository: UserRepository
) : BaseViewModel() {

    private val loading = MutableLiveData<Boolean>()
    private val success = MutableLiveData<Boolean>()

    var firstName = ""
    var lastName = ""
    var username = ""
    var password = ""

    fun isLoading(): LiveData<Boolean> {
        return loading
    }

    fun isSuccess(): LiveData<Boolean> {
        return success
    }

    fun register() {
        loading.value = true
        val disposable = userRepository
            .register(firstName, lastName, username, password)
            .subscribe(
                {
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