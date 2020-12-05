package com.mci.lara.mobile.util

import com.mci.lara.mobile.data.network.*
import com.mci.lara.mobile.data.network.interceptor.HeaderInterceptor
import com.mci.lara.mobile.data.network.interceptor.RefreshInterceptor
import com.mci.lara.mobile.data.repository.*
import com.mci.lara.mobile.view.devices.DevicesViewModel
import com.mci.lara.mobile.view.home.HomeViewModel
import com.mci.lara.mobile.view.login.LoginViewModel
import com.mci.lara.mobile.view.media.MediaViewModel
import com.mci.lara.mobile.view.register.RegisterViewModel
import com.mci.lara.mobile.view.room.RoomViewModel
import com.mci.lara.mobile.view.rooms.RoomsViewModel
import com.mci.lara.mobile.view.splash.SplashViewModel
import org.koin.android.ext.koin.androidApplication
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit

/**
Lara
Created by Catalin on 11/24/2020
 **/
object InjectionModule {

    val appModule = module {

        single { SharedPreferencesUtil(androidApplication()) }
        single { ClientBuilder.createApiClient(get(), get()) }

        single { get<Retrofit>().create(UserClient::class.java) }
        single { get<Retrofit>().create(HouseClient::class.java) }
        single { get<Retrofit>().create(FeatureClient::class.java) }
        single { get<Retrofit>().create(RoomClient::class.java) }

        single { ClientBuilder.createKeycloakClient().create(TokenClient::class.java) }

        single { HeaderInterceptor(get()) }
        single { RefreshInterceptor(get()) }

        single { HouseRepository(get(), get()) }
        single { RoomRepository(get()) }
        single { UserRepository(get(), get(), get()) }
        single { FeatureRepository(get()) }
        single { TokenRepository(get(), get()) }

        viewModel { LoginViewModel(get(), get(), get()) }
        viewModel { RegisterViewModel(get()) }
        viewModel { SplashViewModel(get()) }
        viewModel { RoomsViewModel(get(), get()) }
        viewModel { DevicesViewModel() }
        viewModel { MediaViewModel() }
        viewModel { RoomViewModel(get(), get()) }
        viewModel { HomeViewModel(get(), get()) }

    }

}