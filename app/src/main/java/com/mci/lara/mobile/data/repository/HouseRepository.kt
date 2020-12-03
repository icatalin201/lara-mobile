package com.mci.lara.mobile.data.repository

import com.mci.lara.mobile.data.model.House
import com.mci.lara.mobile.data.network.HouseClient
import com.mci.lara.mobile.util.SharedPreferencesUtil
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.util.*

/**
Lara
Created by Catalin on 11/24/2020
 **/
class HouseRepository(
    private val houseClient: HouseClient,
    private val sharedPreferencesUtil: SharedPreferencesUtil
) {

    companion object {
        private const val HOUSE_ID_VALUE_KEY = "LARA.HouseId.Value"
    }

    fun saveHouseId(houseId: UUID) {
        sharedPreferencesUtil.save(HOUSE_ID_VALUE_KEY, houseId.toString())
    }

    fun getHouseId(): UUID {
        val value: String = sharedPreferencesUtil.get(HOUSE_ID_VALUE_KEY, null)
        return Optional.ofNullable(value).map { UUID.fromString(it) }.orElse(null)
    }

    fun getHouse(username: String): Single<House> {
        return houseClient.getHouse(username)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
    }

}