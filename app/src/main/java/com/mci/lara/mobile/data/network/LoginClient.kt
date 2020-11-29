package com.mci.lara.mobile.data.network

import com.mci.lara.mobile.data.network.payload.AuthorizationResponse
import io.reactivex.Single
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

/**
Lara
Created by Catalin on 11/29/2020
 **/
interface LoginClient {

    @FormUrlEncoded
    @POST("auth/realms/lara/protocol/openid-connect/token")
    fun login(
        @Field("username") username: String,
        @Field("password") password: String,
        @Field("grant_type") grantType: String,
        @Field("client_id") clientId: String,
    ): Single<AuthorizationResponse>

}