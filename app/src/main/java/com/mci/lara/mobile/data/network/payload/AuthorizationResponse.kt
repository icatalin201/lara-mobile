package com.mci.lara.mobile.data.network.payload

/**
Lara
Created by Catalin on 11/29/2020
 **/
data class AuthorizationResponse(
    val accessToken: String,
    val refreshToken: String,
    val expiresIn: Int,
    val refreshExpiresIn: Int,
    val tokenType: String,
    val notBeforePolicy: Int,
    val sessionState: String,
    val scope: String
)