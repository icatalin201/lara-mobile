package com.mci.lara.mobile.view.register

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.mci.lara.mobile.data.repository.LaraRepository
import com.mci.lara.mobile.view.BaseViewModel
import kotlinx.coroutines.launch

/**
Lara
Created by Catalin on 11/26/2020
 **/
class RegisterViewModel(
    private val laraRepository: LaraRepository
) : BaseViewModel() {

    private val loading = MutableLiveData<Boolean>()
    private val success = MutableLiveData<Boolean>()

    var firstName = ""
    var lastName = ""
    var username = ""
    var password = ""
    var houseCode = ""

    fun isLoading(): LiveData<Boolean> {
        return loading
    }

    fun isSuccess(): LiveData<Boolean> {
        return success
    }

    fun register() {
        loading.value = true
        viewModelScope.launch {
            val disposable = laraRepository
                .register(firstName, lastName, username, password, houseCode)
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

}