package com.mci.lara.mobile.data.repository

import com.mci.lara.mobile.data.model.House
import com.mci.lara.mobile.data.network.HouseClient
import io.reactivex.Single
import java.util.*

/**
Lara
Created by Catalin on 11/24/2020
 **/
class HouseRepository(
    private val houseClient: HouseClient
) {

    fun getHouse(username: String): Single<House> {
        return Single.fromCallable {
            House(UUID.randomUUID(), "Home Sweet Home")
        }
//        return houseClient.getHouse(username)
//            .observeOn(AndroidSchedulers.mainThread())
//            .subscribeOn(Schedulers.io())
    }

}