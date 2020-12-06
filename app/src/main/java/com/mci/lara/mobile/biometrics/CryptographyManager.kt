package com.mci.lara.mobile.biometrics

import com.mci.lara.mobile.util.SharedPreferencesUtil
import javax.crypto.Cipher

/**
Lara
Created by Catalin on 12/6/2020
 **/
interface CryptographyManager {

    companion object {
        const val SECRET_KEY_NAME = "Crypto.Secret.Key.Name"
        fun create(sharedPreferencesUtil: SharedPreferencesUtil): CryptographyManager {
            return CryptographyManagerImpl(sharedPreferencesUtil)
        }
    }

    fun isActivated(): Boolean

    fun toggleBiometrics(isOn: Boolean)

    fun getInitializedCipherForEncryption(keyName: String): Cipher

    fun getInitializedCipherForDecryption(keyName: String, initializationVector: ByteArray): Cipher

    fun encryptData(plaintext: String, cipher: Cipher): CipherTextWrapper

    fun decryptData(cipherText: ByteArray, cipher: Cipher): String

    fun saveCipherTextWrapper(cipherTextWrapper: CipherTextWrapper)

    fun getCipherTextWrapper(): CipherTextWrapper?

}