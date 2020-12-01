package com.mci.lara.mobile.data.network

import com.mci.lara.mobile.data.model.Feature
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import java.util.*

/**
Lara
Created by Catalin on 11/24/2020
 **/
interface FeatureClient {

    @GET("features/{id}")
    fun getFeature(@Path("id") id: UUID): Single<Feature>

    @GET("features")
    fun getFeatures(@Query("room") roomId: UUID): Single<MutableList<Feature>>

}