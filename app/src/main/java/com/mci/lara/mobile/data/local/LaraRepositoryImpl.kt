package com.mci.lara.mobile.data.local

import com.mci.lara.mobile.data.model.Feature
import com.mci.lara.mobile.data.model.Room
import com.mci.lara.mobile.data.remote.LaraApiClient
import io.reactivex.Flowable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.util.*

/**
Lara
Created by Catalin on 1/10/2021
 **/
class LaraRepositoryImpl(
    private val laraApiClient: LaraApiClient,
    private val sharedPreferencesUtil: SharedPreferencesUtil
) : LaraRepository {

    override fun getRooms(): Flowable<List<Room>> {
        return laraApiClient
            .getRooms()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .toFlowable()
            .doOnError(Throwable::printStackTrace)
    }

    override fun getFeatures(roomId: UUID): Flowable<List<Feature>> {
        return laraApiClient
            .getFeatures(roomId)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .toFlowable()
            .doOnError(Throwable::printStackTrace)
    }

}