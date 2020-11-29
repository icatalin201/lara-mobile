package com.mci.lara.mobile.data.network

import com.mci.lara.mobile.data.network.payload.CreateUserRequest
import com.mci.lara.mobile.data.network.payload.FindUserResponse
import com.mci.lara.mobile.data.network.payload.FindUsersResponse
import io.reactivex.Completable
import io.reactivex.Single
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import java.util.*

/**
Lara
Created by Catalin on 11/24/2020
 **/
interface UserClient {

    @GET("users/{id}")
    fun getUser(@Path("id") id: UUID): Single<FindUserResponse>

    @GET("users")
    fun getUsers(): Single<FindUsersResponse>

    @POST("users")
    fun register(@Body request: CreateUserRequest): Completable

}