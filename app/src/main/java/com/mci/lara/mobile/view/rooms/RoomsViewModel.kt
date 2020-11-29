package com.mci.lara.mobile.view.rooms

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.mci.lara.mobile.data.model.Room
import com.mci.lara.mobile.data.repository.RoomRepository
import com.mci.lara.mobile.data.repository.UserRepository
import com.mci.lara.mobile.view.BaseViewModel

/**
 * Lara
 * Created by Catalin on 11/29/2020
 **/
class RoomsViewModel(
    private val roomRepository: RoomRepository,
    private val userRepository: UserRepository
) : BaseViewModel() {

    private val roomList = MutableLiveData<MutableList<Room>>()

    init {
        val disposable = roomRepository.getRooms(userRepository.getUsername())
            .subscribe({ roomList.value = it }, { it.printStackTrace() })
        compositeDisposable.add(disposable)
    }

    fun getRoomList(): LiveData<MutableList<Room>> {
        return roomList
    }

}