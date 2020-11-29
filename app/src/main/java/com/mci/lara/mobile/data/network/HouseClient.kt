package com.mci.lara.mobile.data.network

import com.mci.lara.mobile.data.network.payload.FindHouseResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path
import java.util.*

/**
Lara
Created by Catalin on 11/24/2020
 **/
interface HouseClient {

    @GET("houses/{id}")
    fun getHouse(@Path("id") id: UUID): Single<FindHouseResponse>

}