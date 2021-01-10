package com.mci.lara.mobile.data.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.mci.lara.mobile.data.model.Room
import java.util.*

/**
Lara
Created by Catalin on 1/10/2021
 **/
@Dao
interface RoomDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun save(room: Room)

    @Query("SELECT * FROM rooms WHERE id = :id")
    fun findById(id: UUID): LiveData<Room>

    @Query("SELECT * FROM rooms")
    fun findAll(): LiveData<List<Room>>
}