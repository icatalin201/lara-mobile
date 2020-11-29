package com.mci.lara.mobile.data.repository

import com.mci.lara.mobile.BuildConfig
import com.mci.lara.mobile.data.network.LoginClient
import com.mci.lara.mobile.data.network.UserClient
import com.mci.lara.mobile.data.network.payload.AuthorizationResponse
import com.mci.lara.mobile.data.network.payload.CreateUserRequest
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
    private val loginClient: LoginClient
) {

    fun login(username: String, password: String): Single<AuthorizationResponse> {
        return loginClient
            .login(username, password, "password", BuildConfig.CLIENT_ID)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
    }

    fun register(
        firstName: String,
        lastName: String,
        username: String,
        password: String
    ): Completable {
        return userClient.register(
            CreateUserRequest(
                firstName,
                lastName,
                username,
                password
            )
        ).observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
    }

}