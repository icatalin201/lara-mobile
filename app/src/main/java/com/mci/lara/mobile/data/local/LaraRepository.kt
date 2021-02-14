package com.mci.lara.mobile.data.local

import com.mci.lara.mobile.data.model.Feature
import com.mci.lara.mobile.data.model.Room
import io.reactivex.Flowable
import java.util.*

/**
Lara
Created by Catalin on 1/10/2021
 **/
interface LaraRepository {
    fun getRooms(): Flowable<List<Room>>
    fun getFeatures(roomId: UUID): Flowable<List<Feature>>
}