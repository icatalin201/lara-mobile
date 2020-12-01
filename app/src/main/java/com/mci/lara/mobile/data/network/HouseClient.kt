package com.mci.lara.mobile.data.network

import com.mci.lara.mobile.data.model.House
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

/**
Lara
Created by Catalin on 11/24/2020
 **/
interface HouseClient {

    @GET("houses")
    fun getHouse(@Query("username") username: String): Single<House>

}