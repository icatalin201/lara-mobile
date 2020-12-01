package com.mci.lara.mobile.view.room

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.mci.lara.mobile.data.model.Feature
import com.mci.lara.mobile.data.model.Room
import com.mci.lara.mobile.data.repository.FeatureRepository
import com.mci.lara.mobile.data.repository.RoomRepository
import com.mci.lara.mobile.view.BaseViewModel
import java.util.*

/**
Lara
Created by Catalin on 11/30/2020
 **/
class RoomViewModel(
    private val roomRepository: RoomRepository,
    private val featureRepository: FeatureRepository
) : BaseViewModel() {

    private val featureList = MutableLiveData<MutableList<Feature>>()
    private val room = MutableLiveData<Room>()
    private var roomId: UUID? = null
    private val runnable: Runnable = Runnable {
        val featuresSubscription = roomId?.let { id ->
            featureRepository
                .getFeatures(id)
                .subscribe({
                    this.featureList.value = it
                    postRunnable()
                }, { it.printStackTrace() })
        }
        featuresSubscription?.let { compositeDisposable.add(it) }
    }

    override fun onCleared() {
        mainHandler.removeCallbacks(runnable)
        super.onCleared()
    }

    fun fetch(roomId: UUID) {
        this.roomId = roomId
        val roomSubscription = roomRepository
            .getRoom(roomId)
            .subscribe({ this.room.value = it }, { it.printStackTrace() })
        compositeDisposable.add(roomSubscription)
        mainHandler.post(runnable)
    }

    fun getRoom(): LiveData<Room> {
        return room
    }

    fun getFeatureList(): LiveData<MutableList<Feature>> {
        return featureList
    }

    private fun postRunnable() {
        mainHandler.postDelayed(runnable, 5000)
    }

}