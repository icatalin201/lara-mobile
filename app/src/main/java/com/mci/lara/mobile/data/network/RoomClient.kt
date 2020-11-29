package com.mci.lara.mobile.data.network

import com.mci.lara.mobile.data.model.Room
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import java.util.*

/**
Lara
Created by Catalin on 11/24/2020
 **/
interface RoomClient {

    @GET("rooms/{id}")
    fun getRoom(@Path("id") id: UUID): Single<Room>

    @GET("rooms")
    fun getRooms(@Query("user") username: String): Single<MutableList<Room>>

}