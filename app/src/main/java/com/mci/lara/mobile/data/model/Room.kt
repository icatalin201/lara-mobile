package com.mci.lara.mobile.data.model

import java.util.*

/**
Lara
Created by Catalin on 11/18/2020
 **/
data class Room(
    val id: UUID,
    val name: String,
    val type: RoomType,
    val features: List<FeatureData>
)