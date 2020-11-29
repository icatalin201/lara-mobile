package com.mci.lara.mobile.data.model

import java.util.*

/**
Lara
Created by Catalin on 11/18/2020
 **/
data class User(
        val id: UUID,
        val firstName: String,
        val lastName: String,
        val role: UserRole
)