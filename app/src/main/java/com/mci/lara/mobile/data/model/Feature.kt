package com.mci.lara.mobile.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

/**
Lara
Created by Catalin on 11/18/2020
 **/
@Entity(tableName = "features")
data class Feature(
    @ColumnInfo
    @PrimaryKey(autoGenerate = false)
    val id: UUID,
    @ColumnInfo
    val active: Boolean,
    @ColumnInfo
    val name: String,
    @ColumnInfo
    val type: FeatureType,
    @ColumnInfo
    val value: Double?,
    @ColumnInfo
    val unit: String,
    @ColumnInfo
    val recordedOn: String?,
    @ColumnInfo
    val roomId: UUID
)