package com.mci.lara.mobile.data.network

import com.mci.lara.mobile.data.network.payload.FindFeatureResponse
import com.mci.lara.mobile.data.network.payload.FindFeaturesResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path
import java.util.*

/**
Lara
Created by Catalin on 11/24/2020
 **/
interface FeatureClient {

    @GET("features/{id}")
    fun getFeature(@Path("id") id: UUID): Single<FindFeatureResponse>

    @GET("features")
    fun getFeatures(): Single<FindFeaturesResponse>

}