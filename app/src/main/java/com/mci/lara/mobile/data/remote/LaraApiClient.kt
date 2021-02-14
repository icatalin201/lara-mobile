package com.mci.lara.mobile.data.remote

import com.mci.lara.mobile.data.model.Feature
import com.mci.lara.mobile.data.model.Room
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path
import java.util.*

/**
Lara
Created by Catalin on 1/10/2021
 **/
interface LaraApiClient {
    @GET("rooms")
    fun getRooms(): Single<List<Room>>

    @GET("rooms/{roomId}/features")
    fun getFeatures(@Path("roomId") roomId: UUID): Single<List<Feature>>
}