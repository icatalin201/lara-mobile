package com.mci.lara.mobile.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.mci.lara.mobile.data.model.Feature
import com.mci.lara.mobile.data.model.House
import com.mci.lara.mobile.data.model.Room
import com.mci.lara.mobile.data.model.User

/**
Lara
Created by Catalin on 1/10/2021
 **/
@Database(
    entities = [
        House::class,
        User::class,
        Room::class,
        Feature::class
    ], version = 1, exportSchema = false
)
@TypeConverters(Converters::class)
abstract class LaraDatabase : RoomDatabase() {

    abstract fun houseDao(): HouseDao
    abstract fun userDao(): UserDao
    abstract fun roomDao(): RoomDao
    abstract fun featureDao(): FeatureDao

    companion object {
        private lateinit var INSTANCE: LaraDatabase

        fun getInstance(context: Context): LaraDatabase {
            if (!::INSTANCE.isInitialized) {
                INSTANCE = androidx.room.Room.databaseBuilder(
                    context.applicationContext,
                    LaraDatabase::class.java,
                    "lara_database"
                ).build()
            }
            return INSTANCE
        }
    }

}