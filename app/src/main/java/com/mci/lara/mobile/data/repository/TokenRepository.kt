package com.mci.lara.mobile.data.repository

import com.mci.lara.mobile.BuildConfig
import com.mci.lara.mobile.data.network.TokenClient
import com.mci.lara.mobile.data.network.payload.AuthorizationResponse
import com.mci.lara.mobile.util.SharedPreferencesUtil
import io.reactivex.Single
import java.time.LocalDateTime
import java.time.ZoneOffset

/**
Lara
Created by Catalin on 11/24/2020
 **/
class TokenRepository(
    private val tokenClient: TokenClient,
    private val sharedPreferencesUtil: SharedPreferencesUtil
) {

    companion object {
        private const val TOKEN_VALUE_KEY = "LARA.Token.Value"
        private const val REFRESH_TOKEN_VALUE_KEY = "LARA.Refresh.Token.Value"
        private const val TOKEN_VALIDITY_KEY = "LARA.Token.Validity"
    }

    fun save(token: String, refreshToken: String, validity: Int) {
        sharedPreferencesUtil.save(TOKEN_VALUE_KEY, token)
        sharedPreferencesUtil.save(REFRESH_TOKEN_VALUE_KEY, refreshToken)
        val validUntil = LocalDateTime.now(ZoneOffset.UTC)
            .plusSeconds(validity.toLong())
        sharedPreferencesUtil.save(
            TOKEN_VALIDITY_KEY, validUntil
                .toEpochSecond(ZoneOffset.UTC)
        )
    }

    fun save(token: String) {
        sharedPreferencesUtil.save(TOKEN_VALUE_KEY, token)
    }

    fun refreshToken(): Single<AuthorizationResponse> {
        val refreshToken = getRefresh()
        return tokenClient.refreshToken(
            refreshToken,
            "refresh_token",
            BuildConfig.CLIENT_ID
        )
    }

    fun get(): String {
        return sharedPreferencesUtil
            .get(TOKEN_VALUE_KEY, "")!!
    }

    fun getRefresh(): String {
        return sharedPreferencesUtil
            .get(REFRESH_TOKEN_VALUE_KEY, "")!!
    }

    fun isValid(): Boolean {
        val expirationDateInSeconds = sharedPreferencesUtil
            .get(TOKEN_VALIDITY_KEY, 0L)
        val expirationDate = LocalDateTime.ofEpochSecond(
            expirationDateInSeconds,
            0, ZoneOffset.UTC
        )
        return LocalDateTime.now(ZoneOffset.UTC).isBefore(expirationDate)
    }

    fun isLogged(): Boolean {
        return get().isNotBlank() && isValid()
    }

    fun invalidate() {
        sharedPreferencesUtil.remove(TOKEN_VALUE_KEY)
        sharedPreferencesUtil.remove(TOKEN_VALIDITY_KEY)
    }

}