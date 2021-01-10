package com.mci.lara.mobile.data.repository

import androidx.lifecycle.LiveData
import com.mci.lara.mobile.data.model.Feature
import com.mci.lara.mobile.data.model.House
import com.mci.lara.mobile.data.model.Room
import com.mci.lara.mobile.data.model.User
import io.reactivex.Completable
import java.util.*

/**
Lara
Created by Catalin on 1/10/2021
 **/
interface LaraRepository {
    suspend fun register(
        firstName: String,
        lastName: String,
        username: String,
        password: String,
        houseCode: String
    ): Completable


    suspend fun saveUser(user: User)
    suspend fun saveHouse(house: House)
    suspend fun saveRoom(room: Room)
    suspend fun saveFeature(feature: Feature)
    fun saveUsername(username: String)
    fun saveHouseId(houseId: UUID)
    fun getUsername(): String?
    fun getHouseId(): UUID?
    fun getHouse(): LiveData<House>
    fun getRoom(id: UUID): LiveData<Room>
    fun getRooms(): LiveData<List<Room>>
    fun getFeatures(roomId: UUID): LiveData<List<Feature>>
    suspend fun syncHouse()
    suspend fun syncRooms()
    suspend fun syncFeatures()
}