package com.mci.lara.mobile.data.network

import com.mci.lara.mobile.data.network.payload.FindRoomResponse
import com.mci.lara.mobile.data.network.payload.FindRoomsResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path
import java.util.*

/**
Lara
Created by Catalin on 11/24/2020
 **/
interface RoomClient {

    @GET("rooms/{id}")
    fun getRoom(@Path("id") id: UUID): Single<FindRoomResponse>

    @GET("rooms")
    fun getRooms(): Single<FindRoomsResponse>

}