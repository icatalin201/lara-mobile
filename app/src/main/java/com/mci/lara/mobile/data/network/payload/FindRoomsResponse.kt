package com.mci.lara.mobile.data.network.payload

import com.mci.lara.mobile.data.model.Room

/**
Lara
Created by Catalin on 11/24/2020
 **/
data class FindRoomsResponse(
    val rooms: MutableList<Room>
)
