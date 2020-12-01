package com.mci.lara.mobile.view.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.mci.lara.mobile.data.model.House
import com.mci.lara.mobile.data.repository.HouseRepository
import com.mci.lara.mobile.data.repository.UserRepository
import com.mci.lara.mobile.view.BaseViewModel

/**
 * Lara
 * Created by Catalin on 12/1/2020
 **/
class HomeViewModel(
    private val houseRepository: HouseRepository,
    private val userRepository: UserRepository
) : BaseViewModel() {

    private val house = MutableLiveData<House>()

    init {
        val subscription = houseRepository.getHouse(userRepository.getUsername())
            .subscribe({ house.value = it }, { it.printStackTrace() })
        compositeDisposable.add(subscription)
    }

    fun getHouse(): LiveData<House> {
        return house
    }

}