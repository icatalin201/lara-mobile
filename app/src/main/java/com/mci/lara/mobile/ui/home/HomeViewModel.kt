package com.mci.lara.mobile.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mci.lara.mobile.data.local.LaraRepository
import com.mci.lara.mobile.data.model.Room
import io.reactivex.disposables.CompositeDisposable

/**
 * Lara
 * Created by Catalin on 12/1/2020
 **/
class HomeViewModel(
    laraRepository: LaraRepository
) : ViewModel() {

    private val compositeDisposable = CompositeDisposable()

    private val _rooms = MutableLiveData<List<Room>>()
    val rooms: LiveData<List<Room>> = _rooms

    init {
        compositeDisposable.add(
            laraRepository.getRooms()
                .subscribe { rooms ->
                    _rooms.value = rooms
                }
        )
    }

    override fun onCleared() {
        compositeDisposable.dispose()
        super.onCleared()
    }

}