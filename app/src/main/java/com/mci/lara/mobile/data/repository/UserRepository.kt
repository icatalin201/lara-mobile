package com.mci.lara.mobile.data.repository

import com.mci.lara.mobile.BuildConfig
import com.mci.lara.mobile.data.network.TokenClient
import com.mci.lara.mobile.data.network.UserClient
import com.mci.lara.mobile.data.network.payload.AuthorizationResponse
import com.mci.lara.mobile.data.network.payload.CreateUserRequest
import com.mci.lara.mobile.util.SharedPreferencesUtil
import io.reactivex.Completable
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
Lara
Created by Catalin on 11/24/2020
 **/
class UserRepository(
    private val userClient: UserClient,
    private val tokenClient: TokenClient,
    private val sharedPreferencesUtil: SharedPreferencesUtil
) {

    companion object {
        private const val USERNAME_VALUE_KEY = "LARA.Username.Value"
    }

    fun saveUsername(username: String) {
        sharedPreferencesUtil.save(USERNAME_VALUE_KEY, username)
    }

    fun getUsername(): String {
        return sharedPreferencesUtil.get(USERNAME_VALUE_KEY, "")
    }

    fun login(username: String, password: String): Single<AuthorizationResponse> {
        return tokenClient
            .login(username, password, "password", BuildConfig.CLIENT_ID)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
    }

    fun register(
        firstName: String,
        lastName: String,
        username: String,
        password: String,
        houseCode: String
    ): Completable {
        return userClient.register(
            CreateUserRequest(
                firstName,
                lastName,
                username,
                password,
                houseCode
            )
        ).observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
    }

}