package com.mci.lara.mobile.view.devices

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.mci.lara.mobile.data.model.Device
import com.mci.lara.mobile.view.BaseViewModel

/**
 * Lara
 * Created by Catalin on 11/29/2020
 **/
class DevicesViewModel(

) : BaseViewModel() {

    private val deviceList = MutableLiveData<MutableList<Device>>()

    init {
        deviceList.value = mutableListOf()
    }

    fun getDeviceList(): LiveData<MutableList<Device>> {
        return deviceList
    }

}