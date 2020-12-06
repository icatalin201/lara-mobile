package com.mci.lara.mobile.view.login

import androidx.biometric.BiometricPrompt
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.mci.lara.mobile.biometrics.BiometricPromptUtils
import com.mci.lara.mobile.biometrics.CipherTextWrapper
import com.mci.lara.mobile.biometrics.CryptographyManager
import com.mci.lara.mobile.biometrics.CryptographyManager.Companion.SECRET_KEY_NAME
import com.mci.lara.mobile.data.repository.HouseRepository
import com.mci.lara.mobile.data.repository.TokenRepository
import com.mci.lara.mobile.data.repository.UserRepository
import com.mci.lara.mobile.view.BaseViewModel

/**
Lara
Created by Catalin on 11/25/2020
 **/
class LoginViewModel(
    private val userRepository: UserRepository,
    private val tokenRepository: TokenRepository,
    private val houseRepository: HouseRepository,
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
        val disposable = userRepository
            .login(username, password)
            .subscribe(
                { response ->
                    tokenRepository.save(
                        response.accessToken,
                        response.refreshToken,
                        response.expiresIn
                    )
                    houseRepository.getHouse(username)
                        .subscribe(
                            { house ->
                                houseRepository.saveHouseId(house.id)
                                userRepository.saveUsername(username)
                                loading.value = false
                                success.value = true
                            },
                            {
                                it.printStackTrace()
                                loading.value = false
                                success.value = false
                            }
                        )
                },
                {
                    it.printStackTrace()
                    loading.value = false
                    success.value = false
                }
            )
        compositeDisposable.add(disposable)
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
            ) { decryptServerTokenFromStorage(textWrapper, it) }
            val promptInfo = BiometricPromptUtils.createPromptInfo(activity)
            biometricPrompt.authenticate(promptInfo, BiometricPrompt.CryptoObject(cipher))
        }
    }

    private fun decryptServerTokenFromStorage(
        textWrapper: CipherTextWrapper,
        authResult: BiometricPrompt.AuthenticationResult
    ) {
        authResult.cryptoObject?.cipher?.let {
            val token = cryptographyManager.decryptData(textWrapper.cipherText, it)
            val username = userRepository.getUsername()
            tokenRepository.save(token)
            loading.value = true
            houseRepository.getHouse(username)
                .subscribe(
                    { house ->
                        houseRepository.saveHouseId(house.id)
                        userRepository.saveUsername(username)
                        loading.value = false
                        success.value = true
                    },
                    { error ->
                        error.printStackTrace()
                        loading.value = false
                        success.value = false
                    }
                )
        }
    }
}