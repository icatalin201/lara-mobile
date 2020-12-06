package com.mci.lara.mobile.biometrics

import android.security.keystore.KeyGenParameterSpec
import android.security.keystore.KeyProperties
import com.google.gson.Gson
import com.mci.lara.mobile.util.SharedPreferencesUtil
import java.nio.charset.Charset
import java.security.KeyStore
import javax.crypto.Cipher
import javax.crypto.KeyGenerator
import javax.crypto.SecretKey
import javax.crypto.spec.GCMParameterSpec

/**
Lara
Created by Catalin on 12/6/2020
 **/
class CryptographyManagerImpl(
    private val sharedPreferences: SharedPreferencesUtil
) : CryptographyManager {

    companion object {
        private const val KEY_SIZE = 256
        private const val ANDROID_KEYSTORE = "AndroidKeyStore"
        private const val ENCRYPTION_BLOCK_MODE = KeyProperties.BLOCK_MODE_GCM
        private const val ENCRYPTION_PADDING = KeyProperties.ENCRYPTION_PADDING_NONE
        private const val ENCRYPTION_ALGORITHM = KeyProperties.KEY_ALGORITHM_AES
        private const val BIOMETRICS_CIPHER_WRAPPER = "Biometrics.Cipher.Wrapper"
        private const val BIOMETRICS_ON = "Biometrics.On"
    }

    override fun isActivated(): Boolean {
        return sharedPreferences.get(BIOMETRICS_ON, false)
    }

    override fun toggleBiometrics(isOn: Boolean) {
        sharedPreferences.save(BIOMETRICS_ON, isOn)
    }

    override fun getInitializedCipherForEncryption(keyName: String): Cipher {
        val cipher = getCipher()
        val secretKey = getOrCreateSecretKey(keyName)
        cipher.init(Cipher.ENCRYPT_MODE, secretKey)
        return cipher
    }

    override fun getInitializedCipherForDecryption(
        keyName: String,
        initializationVector: ByteArray
    ): Cipher {
        val cipher = getCipher()
        val secretKey = getOrCreateSecretKey(keyName)
        cipher.init(Cipher.DECRYPT_MODE, secretKey, GCMParameterSpec(128, initializationVector))
        return cipher
    }

    override fun encryptData(plaintext: String, cipher: Cipher): CipherTextWrapper {
        val cipherText = cipher.doFinal(plaintext.toByteArray(Charset.forName("UTF-8")))
        return CipherTextWrapper(cipherText, cipher.iv)
    }

    override fun decryptData(cipherText: ByteArray, cipher: Cipher): String {
        val plaintext = cipher.doFinal(cipherText)
        return String(plaintext, Charset.forName("UTF-8"))
    }

    private fun getCipher(): Cipher {
        val transformation = "$ENCRYPTION_ALGORITHM/$ENCRYPTION_BLOCK_MODE/$ENCRYPTION_PADDING"
        return Cipher.getInstance(transformation)
    }

    private fun getOrCreateSecretKey(keyName: String): SecretKey {
        val keyStore = KeyStore.getInstance(ANDROID_KEYSTORE)
        keyStore.load(null)
        keyStore.getKey(keyName, null)?.let { return it as SecretKey }
        val paramsBuilder = KeyGenParameterSpec.Builder(
            keyName,
            KeyProperties.PURPOSE_ENCRYPT or KeyProperties.PURPOSE_DECRYPT
        )
        paramsBuilder.apply {
            setBlockModes(ENCRYPTION_BLOCK_MODE)
            setEncryptionPaddings(ENCRYPTION_PADDING)
            setKeySize(KEY_SIZE)
            setUserAuthenticationRequired(true)
        }

        val keyGenParams = paramsBuilder.build()
        val keyGenerator = KeyGenerator.getInstance(
            KeyProperties.KEY_ALGORITHM_AES,
            ANDROID_KEYSTORE
        )
        keyGenerator.init(keyGenParams)
        return keyGenerator.generateKey()
    }

    override fun saveCipherTextWrapper(
        cipherTextWrapper: CipherTextWrapper,
    ) {
        val json = Gson().toJson(cipherTextWrapper)
        sharedPreferences.save(BIOMETRICS_CIPHER_WRAPPER, json)
    }

    override fun getCipherTextWrapper(): CipherTextWrapper? {
        val json = sharedPreferences.get(BIOMETRICS_CIPHER_WRAPPER, "")
        return Gson().fromJson(json, CipherTextWrapper::class.java)
    }
}