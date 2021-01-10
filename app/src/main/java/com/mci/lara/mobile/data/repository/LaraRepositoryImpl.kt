package com.mci.lara.mobile.data.repository

import androidx.lifecycle.LiveData
import com.mci.lara.mobile.data.database.FeatureDao
import com.mci.lara.mobile.data.database.HouseDao
import com.mci.lara.mobile.data.database.RoomDao
import com.mci.lara.mobile.data.database.UserDao
import com.mci.lara.mobile.data.model.Feature
import com.mci.lara.mobile.data.model.House
import com.mci.lara.mobile.data.model.Room
import com.mci.lara.mobile.data.model.User
import com.mci.lara.mobile.data.network.LaraApiClient
import com.mci.lara.mobile.data.network.payload.CreateUserRequest
import com.mci.lara.mobile.util.SharedPreferencesUtil
import io.reactivex.Completable
import java.util.*

/**
Lara
Created by Catalin on 1/10/2021
 **/
class LaraRepositoryImpl(
    private val userDao: UserDao,
    private val houseDao: HouseDao,
    private val roomDao: RoomDao,
    private val featureDao: FeatureDao,
    private val laraApiClient: LaraApiClient,
    private val sharedPreferencesUtil: SharedPreferencesUtil
) : LaraRepository {

    companion object {
        private const val HOUSE_ID_VALUE_KEY = "LARA.HouseId.Value"
        private const val USERNAME_VALUE_KEY = "LARA.Username.Value"
    }

    override suspend fun register(
        firstName: String,
        lastName: String,
        username: String,
        password: String,
        houseCode: String
    ): Completable {
        return laraApiClient.register(
            CreateUserRequest(
                firstName,
                lastName,
                username,
                password,
                houseCode
            )
        )
    }

    override suspend fun saveUser(user: User) {
        userDao.save(user)
    }

    override suspend fun saveHouse(house: House) {
        houseDao.save(house)
    }

    override suspend fun saveRoom(room: Room) {
        roomDao.save(room)
    }

    override suspend fun saveFeature(feature: Feature) {
        featureDao.save(feature)
    }

    override fun saveUsername(username: String) {
        sharedPreferencesUtil.save(USERNAME_VALUE_KEY, username)
    }

    override fun saveHouseId(houseId: UUID) {
        sharedPreferencesUtil.save(HOUSE_ID_VALUE_KEY, houseId.toString())
    }

    override fun getUsername(): String? {
        return sharedPreferencesUtil.get(USERNAME_VALUE_KEY)
    }

    override fun getHouseId(): UUID? {
        val value: String? = sharedPreferencesUtil.get(HOUSE_ID_VALUE_KEY)
        return Optional.ofNullable(value).map { UUID.fromString(it) }.orElse(null)
    }

    override fun getHouse(): LiveData<House> {
        return houseDao.getHouse()
    }

    override fun getRoom(id: UUID): LiveData<Room> {
        return roomDao.findById(id)
    }

    override fun getRooms(): LiveData<List<Room>> {
        return roomDao.findAll()
    }

    override fun getFeatures(roomId: UUID): LiveData<List<Feature>> {
        return featureDao.findAllByRoom(roomId)
    }

    @Throws(RuntimeException::class)
    override suspend fun syncData() {
        val username = getUsername()
        username?.let {
            val house = laraApiClient.getHouse(username).blockingGet()
            saveHouseId(house.id)
            saveHouse(house)
            val rooms = laraApiClient.getRooms(house.id).blockingGet()
            rooms.forEach { room ->
                saveRoom(room)
                val features = laraApiClient.getFeatures(room.id).blockingGet()
                features.forEach { feature -> saveFeature(feature) }
            }
        }
    }
}