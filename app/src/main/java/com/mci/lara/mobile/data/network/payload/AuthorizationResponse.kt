package com.mci.lara.mobile.data.network.payload

import com.google.gson.annotations.SerializedName

/**
Lara
Created by Catalin on 11/29/2020
 **/
data class AuthorizationResponse(
    @SerializedName("access_token")
    val accessToken: String,
    @SerializedName("refresh_token")
    val refreshToken: String,
    @SerializedName("expires_in")
    val expiresIn: Int,
    @SerializedName("refresh_expires_in")
    val refreshExpiresIn: Int,
    @SerializedName("token_type")
    val tokenType: String,
    @SerializedName("not_before_policy")
    val notBeforePolicy: Int,
    @SerializedName("session_state")
    val sessionState: String,
    val scope: String
)