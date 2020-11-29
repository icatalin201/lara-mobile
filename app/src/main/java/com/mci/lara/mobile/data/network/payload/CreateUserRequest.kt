package com.mci.lara.mobile.data.network.payload

/**
Lara
Created by Catalin on 11/25/2020
 **/
data class CreateUserRequest(
    val firstName: String,
    val lastName: String,
    val username: String,
    val password: String
)