package com.mci.lara.mobile.view.settings

import androidx.biometric.BiometricManager
import androidx.biometric.BiometricPrompt
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.mci.lara.mobile.biometrics.BiometricPromptUtils
import com.mci.lara.mobile.biometrics.CryptographyManager
import com.mci.lara.mobile.data.repository.TokenRepository
import com.mci.lara.mobile.view.BaseViewModel

/**
Lara
Created by Catalin on 12/6/2020
 **/
class SettingsViewModel(
    private val cryptographyManager: CryptographyManager,
    private val tokenRepository: TokenRepository
) : BaseViewModel() {

    private val biometricsEnabled = MutableLiveData<Boolean>()

    init {
        biometricsEnabled.value = cryptographyManager.isActivated()
    }

    fun isBiometricsEnabled(): LiveData<Boolean> {
        return biometricsEnabled
    }

    fun toggleBiometrics(isOn: Boolean) {
        cryptographyManager.toggleBiometrics(isOn)
    }

    fun showBiometricPromptForEncryption(
        activity: SettingsActivity,
        successRunnable: Runnable,
        errorRunnable: Runnable
    ) {
        val canAuthenticate = BiometricManager.from(activity).canAuthenticate()
        if (canAuthenticate == BiometricManager.BIOMETRIC_SUCCESS) {
            val cipher =
                cryptographyManager.getInitializedCipherForEncryption(CryptographyManager.SECRET_KEY_NAME)
            val biometricPrompt =
                BiometricPromptUtils.createBiometricPrompt(
                    activity, { successRunnable.run() }
                ) { errorRunnable.run() }
            val promptInfo = BiometricPromptUtils.createPromptInfo(activity)
            biometricPrompt.authenticate(promptInfo, BiometricPrompt.CryptoObject(cipher))
        }
    }

}