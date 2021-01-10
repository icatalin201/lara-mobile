package com.mci.lara.mobile.data.repository

import io.reactivex.Completable

/**
Lara
Created by Catalin on 1/10/2021
 **/
interface TokenRepository {
    suspend fun generateToken(username: String, password: String): Completable
    fun refreshToken()
    fun saveToken(token: String, validity: Int)
    fun saveRefreshToken(refreshToken: String)
    fun getToken(): String
    fun getRefreshToken(): String
}