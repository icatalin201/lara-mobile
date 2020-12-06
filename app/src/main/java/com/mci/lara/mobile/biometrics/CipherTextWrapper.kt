package com.mci.lara.mobile.biometrics

/**
Lara
Created by Catalin on 12/6/2020
 **/
data class CipherTextWrapper(
    val cipherText: ByteArray,
    val initializationVector: ByteArray
)
