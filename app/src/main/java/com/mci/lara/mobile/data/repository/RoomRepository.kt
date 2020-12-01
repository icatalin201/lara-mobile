package com.mci.lara.mobile.data.repository

import com.mci.lara.mobile.data.model.Room
import com.mci.lara.mobile.data.model.RoomType
import com.mci.lara.mobile.data.network.RoomClient
import io.reactivex.Single
import java.util.*

/**
Lara
Created by Catalin on 11/24/2020
 **/
class RoomRepository(
    private val roomClient: RoomClient
) {

    fun getRoom(id: UUID): Single<Room> {
        return Single.fromCallable {
            Room(UUID.randomUUID(), "Bedroom", true, RoomType.BEDROOM)
        }
//        return roomClient.getRoom(id)
//            .observeOn(AndroidSchedulers.mainThread())
//            .subscribeOn(Schedulers.io())
    }

    fun getRooms(username: String): Single<MutableList<Room>> {
        return Single.fromCallable {
            mutableListOf(
                Room(UUID.randomUUID(), "Bedroom", true, RoomType.BEDROOM),
                Room(UUID.randomUUID(), "Kitchen", false, RoomType.KITCHEN),
                Room(UUID.randomUUID(), "Bathroom", false, RoomType.BATHROOM),
                Room(UUID.randomUUID(), "Living room", false, RoomType.LIVING_ROOM)
            )
        }
//        return roomClient.getRooms(username)
//            .observeOn(AndroidSchedulers.mainThread())
//            .subscribeOn(Schedulers.io())
    }

}