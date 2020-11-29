package com.mci.lara.mobile.data.repository

import com.mci.lara.mobile.data.model.House
import com.mci.lara.mobile.data.network.HouseClient
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.util.*

/**
Lara
Created by Catalin on 11/24/2020
 **/
class HouseRepository(
    private val houseClient: HouseClient
) {

    fun getHouse(id: UUID): Single<House> {
        return houseClient.getHouse(id)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .map { response -> response.house }
    }

}