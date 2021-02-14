package com.mci.lara.mobile.data

import com.mci.lara.mobile.data.local.LaraRepository
import com.mci.lara.mobile.data.local.LaraRepositoryImpl
import com.mci.lara.mobile.data.local.SharedPreferencesUtil
import com.mci.lara.mobile.data.remote.ClientBuilder
import com.mci.lara.mobile.data.remote.LaraApiClient
import com.mci.lara.mobile.ui.home.HomeViewModel
import com.mci.lara.mobile.ui.room.RoomViewModel
import org.koin.android.ext.koin.androidApplication
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

/**
Lara
Created by Catalin on 11/24/2020
 **/
object Injection {

    val appModule = module {

        single { SharedPreferencesUtil(androidApplication()) }

        single<LaraApiClient> {
            ClientBuilder.createApiClient().create(LaraApiClient::class.java)
        }
        single<LaraRepository> { LaraRepositoryImpl(get(), get()) }
        viewModel { HomeViewModel(get()) }
        viewModel { RoomViewModel(get()) }
    }

}