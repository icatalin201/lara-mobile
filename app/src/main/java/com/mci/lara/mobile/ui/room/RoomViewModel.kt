package com.mci.lara.mobile.ui.room

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mci.lara.mobile.data.local.LaraRepository
import com.mci.lara.mobile.data.model.Feature
import io.reactivex.disposables.CompositeDisposable
import java.util.*

/**
 * Lara
 * Created by Catalin on 2/14/2021
 **/
class RoomViewModel(
    private val laraRepository: LaraRepository
) : ViewModel() {

    private val compositeDisposable = CompositeDisposable()

    private val _features = MutableLiveData<List<Feature>>()
    val features: LiveData<List<Feature>> = _features

    fun getFeatures(roomId: UUID) {
        compositeDisposable.add(
            laraRepository.getFeatures(roomId)
                .subscribe { features ->
                    _features.value = features
                }
        )
    }

    override fun onCleared() {
        compositeDisposable.dispose()
        super.onCleared()
    }

}