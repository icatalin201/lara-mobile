package com.mci.lara.mobile.data.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.mci.lara.mobile.data.model.Feature
import java.util.*

/**
Lara
Created by Catalin on 1/10/2021
 **/
@Dao
interface FeatureDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun save(feature: Feature)

    @Query("SELECT * FROM features WHERE roomId = :roomId")
    fun findAllByRoom(roomId: UUID): LiveData<List<Feature>>

}