package com.mci.lara.mobile.data.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.mci.lara.mobile.data.model.House

/**
Lara
Created by Catalin on 1/10/2021
 **/
@Dao
interface HouseDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun save(house: House)

    @Query("SELECT * FROM house")
    fun getHouse(): LiveData<House>

}