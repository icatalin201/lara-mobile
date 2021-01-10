package com.mci.lara.mobile.view.login

import androidx.biometric.BiometricPrompt
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.mci.lara.mobile.biometrics.BiometricPromptUtils
import com.mci.lara.mobile.biometrics.CryptographyManager
import com.mci.lara.mobile.biometrics.CryptographyManager.Companion.SECRET_KEY_NAME
import com.mci.lara.mobile.data.repository.LaraRepository
import com.mci.lara.mobile.data.repository.TokenRepository
import com.mci.lara.mobile.view.BaseViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

/**
Lara
Created by Catalin on 11/25/2020
 **/
class LoginViewModel(
    private val laraRepository: LaraRepository,
    private val tokenRepository: TokenRepository,
    private val cryptographyManager: CryptographyManager
) : BaseViewModel() {

    private val loading = MutableLiveData<Boolean>()
    private val success = MutableLiveData<Boolean>()
    private val biometricsEnabled = MutableLiveData<Boolean>()

    init {
        biometricsEnabled.value = cryptographyManager.isActivated()
    }

    var username = ""
    var password = ""

    fun isLoading(): LiveData<Boolean> {
        return loading
    }

    fun isSuccess(): LiveData<Boolean> {
        return success
    }

    fun isBiometricsEnabled(): LiveData<Boolean> {
        return biometricsEnabled
    }

    fun login() {
        loading.value = true
        viewModelScope.launch(Dispatchers.IO) {
            val disposable = tokenRepository
                .generateToken(username, password)
                .subscribe(
                    { viewModelScope.launch { syncData() } },
                    {
                        viewModelScope.launch {
                            it.printStackTrace()
                            loading.value = false
                            success.value = false
                        }
                    }
                )
            compositeDisposable.add(disposable)
        }
    }

    fun showBiometricPromptForDecryption(
        activity: LoginActivity
    ) {
        val cipherTextWrapper = cryptographyManager.getCipherTextWrapper()
        cipherTextWrapper?.let { textWrapper ->
            val cipher = cryptographyManager.getInitializedCipherForDecryption(
                SECRET_KEY_NAME, textWrapper.initializationVector
            )
            val biometricPrompt = BiometricPromptUtils.createBiometricPrompt(
                activity
            ) {
                viewModelScope.launch {
                    syncData()
                }
            }
            val promptInfo = BiometricPromptUtils.createPromptInfo(activity)
            biometricPrompt.authenticate(promptInfo, BiometricPrompt.CryptoObject(cipher))
        }
    }

    private suspend fun syncData() {
        withContext(Dispatchers.IO) {
            viewModelScope.launch {
                loading.value = true
            }
            laraRepository.syncHouse()
            laraRepository.syncRooms()
            laraRepository.syncFeatures()
            viewModelScope.launch {
                loading.value = false
                success.value = true
            }
        }
    }
}