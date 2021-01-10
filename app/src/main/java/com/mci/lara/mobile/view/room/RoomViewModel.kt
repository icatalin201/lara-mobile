package com.mci.lara.mobile.view.room

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import com.mci.lara.mobile.data.model.Feature
import com.mci.lara.mobile.data.model.Room
import com.mci.lara.mobile.data.repository.LaraRepository
import com.mci.lara.mobile.view.BaseViewModel
import java.util.*

/**
Lara
Created by Catalin on 11/30/2020
 **/
class RoomViewModel(
    private val laraRepository: LaraRepository
) : BaseViewModel() {

    private val featureList = MediatorLiveData<List<Feature>>()
    private val room = MediatorLiveData<Room>()

    fun fetch(roomId: UUID) {
        featureList.addSource(laraRepository.getFeatures(roomId)) { featureList.value = it }
        room.addSource(laraRepository.getRoom(roomId)) { room.value = it }
    }

    fun getRoom(): LiveData<Room> {
        return room
    }

    fun getFeatureList(): LiveData<List<Feature>> {
        return featureList
    }

}