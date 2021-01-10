package com.mci.lara.mobile.data.database

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.mci.lara.mobile.data.model.FeatureType
import com.mci.lara.mobile.data.model.RoomType
import com.mci.lara.mobile.data.model.UserRole
import java.util.*

/**
Lara
Created by Catalin on 1/10/2021
 **/
object Converters {

    private val gson = Gson()

    @JvmStatic
    @TypeConverter
    fun convertUUIDToString(uuid: UUID): String {
        return uuid.toString()
    }

    @JvmStatic
    @TypeConverter
    fun convertUUIDFromString(uuid: String): UUID {
        return UUID.fromString(uuid)
    }

    @JvmStatic
    @TypeConverter
    fun convertFeatureTypeToString(featureType: FeatureType): String {
        return featureType.name
    }

    @JvmStatic
    @TypeConverter
    fun convertFeatureTypeFromString(featureType: String): FeatureType {
        return FeatureType.valueOf(featureType)
    }

    @JvmStatic
    @TypeConverter
    fun convertRoomTypeToString(roomType: RoomType): String {
        return roomType.name
    }

    @JvmStatic
    @TypeConverter
    fun convertRoomTypeFromString(roomType: String): RoomType {
        return RoomType.valueOf(roomType)
    }

    @JvmStatic
    @TypeConverter
    fun convertUserRoleToString(userRole: UserRole): String {
        return userRole.name
    }

    @JvmStatic
    @TypeConverter
    fun convertUserRoleFromString(userRole: String): UserRole {
        return UserRole.valueOf(userRole)
    }

}