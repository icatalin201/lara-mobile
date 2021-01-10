package com.mci.lara.mobile.data.network

import com.mci.lara.mobile.data.model.Feature
import com.mci.lara.mobile.data.model.House
import com.mci.lara.mobile.data.model.Room
import com.mci.lara.mobile.data.model.User
import com.mci.lara.mobile.data.network.payload.CreateUserRequest
import io.reactivex.Completable
import io.reactivex.Single
import retrofit2.http.*
import java.util.*

/**
Lara
Created by Catalin on 1/10/2021
 **/
interface LaraApiClient {

    @GET("houses")
    fun getHouse(@Query("username") username: String): Single<House>

    @GET("rooms/{id}")
    fun getRoom(@Path("id") id: UUID): Single<Room>

    @GET("rooms")
    fun getRooms(@Query("house") house: UUID): Single<MutableList<Room>>

    @GET("features/{id}")
    fun getFeature(@Path("id") id: UUID): Single<Feature>

    @GET("features")
    fun getFeatures(@Query("room") roomId: UUID): Single<MutableList<Feature>>

    @GET("users/{id}")
    fun getUser(@Path("id") id: UUID): Single<User>

    @GET("users")
    fun getUser(@Query("username") username: String): Single<User>

    @GET("users")
    fun getUsers(@Query("house") house: UUID): Single<MutableList<User>>

    @POST("users")
    fun register(@Body request: CreateUserRequest): Completable

}