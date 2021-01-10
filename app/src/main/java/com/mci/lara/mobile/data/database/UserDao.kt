package com.mci.lara.mobile.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import com.mci.lara.mobile.data.model.User

/**
Lara
Created by Catalin on 1/10/2021
 **/
@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun save(user: User)

}