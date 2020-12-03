package com.mci.lara.mobile.data.repository

import com.mci.lara.mobile.data.model.Room
import com.mci.lara.mobile.data.network.RoomClient
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.util.*

/**
Lara
Created by Catalin on 11/24/2020
 **/
class RoomRepository(
    private val roomClient: RoomClient
) {

    fun getRoom(id: UUID): Single<Room> {
        return roomClient.getRoom(id)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
    }

    fun getRooms(houseId: UUID): Single<MutableList<Room>> {
        return roomClient.getRooms(houseId)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
    }

}