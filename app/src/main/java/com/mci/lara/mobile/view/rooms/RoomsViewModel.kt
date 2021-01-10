package com.mci.lara.mobile.view.rooms

import androidx.lifecycle.LiveData
import com.mci.lara.mobile.data.model.Room
import com.mci.lara.mobile.data.repository.LaraRepository
import com.mci.lara.mobile.view.BaseViewModel

/**
 * Lara
 * Created by Catalin on 11/29/2020
 **/
class RoomsViewModel(
    laraRepository: LaraRepository
) : BaseViewModel() {

    private val roomList = laraRepository.getRooms()

    fun getRoomList(): LiveData<List<Room>> {
        return roomList
    }

}