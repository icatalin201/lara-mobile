package com.mci.lara.mobile.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

/**
Lara
Created by Catalin on 11/18/2020
 **/
@Entity(tableName = "rooms")
data class Room(
    @ColumnInfo
    @PrimaryKey(autoGenerate = false)
    val id: UUID,
    @ColumnInfo
    val name: String,
    @ColumnInfo
    val enabled: Boolean,
    @ColumnInfo
    val type: RoomType,
)