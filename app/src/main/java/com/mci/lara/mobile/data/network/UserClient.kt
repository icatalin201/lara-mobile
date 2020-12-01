package com.mci.lara.mobile.data.network

import com.mci.lara.mobile.data.model.User
import com.mci.lara.mobile.data.network.payload.CreateUserRequest
import io.reactivex.Completable
import io.reactivex.Single
import retrofit2.http.*
import java.util.*

/**
Lara
Created by Catalin on 11/24/2020
 **/
interface UserClient {

    @GET("users/{id}")
    fun getUser(@Path("id") id: UUID): Single<User>

    @GET("users")
    fun getUser(@Query("username") username: String): Single<User>

    @GET("users")
    fun getUsers(@Query("house") house: UUID): Single<MutableList<User>>

    @POST("users")
    fun register(@Body request: CreateUserRequest): Completable

}