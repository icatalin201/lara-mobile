package com.mci.lara.mobile.data.repository

import com.mci.lara.mobile.data.model.Feature
import com.mci.lara.mobile.data.model.FeatureType
import com.mci.lara.mobile.data.network.FeatureClient
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.time.LocalDateTime
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
    }

    fun getFeatures(roomId: UUID): Single<MutableList<Feature>> {
        return Single.fromCallable {
            mutableListOf(
                Feature(
                    UUID.randomUUID(),
                    true,
                    "Temperatura",
                    FeatureType.TEMPERATURE,
                    20.0,
                    "°C",
                    LocalDateTime.now()
                ),
                Feature(
                    UUID.randomUUID(),
                    true,
                    "Umiditate",
                    FeatureType.HUMIDITY,
                    35.0,
                    "%",
                    LocalDateTime.now()
                )
            )
        }
//        return featureClient.getFeatures(roomId)
//            .observeOn(AndroidSchedulers.mainThread())
//            .subscribeOn(Schedulers.io())
    }

}