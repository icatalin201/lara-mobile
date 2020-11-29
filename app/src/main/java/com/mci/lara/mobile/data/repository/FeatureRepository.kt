package com.mci.lara.mobile.data.repository

import com.mci.lara.mobile.data.model.Feature
import com.mci.lara.mobile.data.network.FeatureClient
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.util.*

/**
Lara
Created by Catalin on 11/24/2020
 **/
class FeatureRepository(
    private val featureClient: FeatureClient
) {

    fun getFeature(id: UUID): Single<Feature> {
        return featureClient.getFeature(id)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .map { response -> response.feature }
    }

    fun getFeatures(): Single<MutableList<Feature>> {
        return featureClient.getFeatures()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .map { response -> response.features }
    }

}