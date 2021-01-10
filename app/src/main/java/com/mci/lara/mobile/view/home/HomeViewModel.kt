package com.mci.lara.mobile.view.home

import androidx.lifecycle.LiveData
import com.mci.lara.mobile.data.model.House
import com.mci.lara.mobile.data.repository.LaraRepository
import com.mci.lara.mobile.view.BaseViewModel

/**
 * Lara
 * Created by Catalin on 12/1/2020
 **/
class HomeViewModel(
    laraRepository: LaraRepository
) : BaseViewModel() {

    private val house = laraRepository.getHouse()

    fun getHouse(): LiveData<House> {
        return house
    }

}