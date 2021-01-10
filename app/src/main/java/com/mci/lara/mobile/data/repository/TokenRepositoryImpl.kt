package com.mci.lara.mobile.data.repository

import com.mci.lara.mobile.BuildConfig
import com.mci.lara.mobile.data.network.TokenClient
import com.mci.lara.mobile.util.SharedPreferencesUtil
import io.reactivex.Completable
import java.time.LocalDateTime
import java.time.ZoneOffset

/**
Lara
Created by Catalin on 1/10/2021
 **/
class TokenRepositoryImpl(
    private val sharedPreferencesUtil: SharedPreferencesUtil,
    private val tokenClient: TokenClient
) : TokenRepository {

    companion object {
        private const val TOKEN_VALUE_KEY = "LARA.Token.Value"
        private const val REFRESH_TOKEN_VALUE_KEY = "LARA.Refresh.Token.Value"
        private const val TOKEN_VALIDITY_KEY = "LARA.Token.Validity"
    }

    override suspend fun generateToken(
        username: String,
        password: String
    ): Completable {
        return Completable.fromRunnable {
            val response = tokenClient
                .login(username, password, "password", BuildConfig.CLIENT_ID)
                .blockingGet()
            saveToken(response.accessToken, response.expiresIn)
            saveRefreshToken(response.refreshToken)
        }
    }

    override fun refreshToken() {
        val refreshToken = getRefreshToken()
        val response = tokenClient
            .refreshToken(refreshToken, "refresh_token", BuildConfig.CLIENT_ID)
            .blockingGet()
        saveToken(response.accessToken, response.expiresIn)
        saveRefreshToken(response.refreshToken)
    }

    override fun saveToken(token: String, validity: Int) {
        sharedPreferencesUtil.save(TOKEN_VALUE_KEY, token)
        val validUntil = LocalDateTime.now(ZoneOffset.UTC)
            .plusSeconds(validity.toLong())
        sharedPreferencesUtil.save(
            TOKEN_VALIDITY_KEY, validUntil
                .toEpochSecond(ZoneOffset.UTC)
        )
    }

    override fun saveRefreshToken(refreshToken: String) {
        sharedPreferencesUtil.save(REFRESH_TOKEN_VALUE_KEY, refreshToken)
    }

    override fun getToken(): String {
        return sharedPreferencesUtil
            .get(TOKEN_VALUE_KEY) ?: "token"
    }

    override fun getRefreshToken(): String {
        return sharedPreferencesUtil
            .get(REFRESH_TOKEN_VALUE_KEY) ?: "token"
    }
}