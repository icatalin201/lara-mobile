package com.mci.lara.mobile.util

import com.mci.lara.mobile.biometrics.CryptographyManager
import com.mci.lara.mobile.data.database.LaraDatabase
import com.mci.lara.mobile.data.network.ClientBuilder
import com.mci.lara.mobile.data.network.LaraApiClient
import com.mci.lara.mobile.data.network.TokenClient
import com.mci.lara.mobile.data.network.interceptor.HeaderInterceptor
import com.mci.lara.mobile.data.network.interceptor.RefreshInterceptor
import com.mci.lara.mobile.data.repository.LaraRepository
import com.mci.lara.mobile.data.repository.LaraRepositoryImpl
import com.mci.lara.mobile.data.repository.TokenRepository
import com.mci.lara.mobile.data.repository.TokenRepositoryImpl
import com.mci.lara.mobile.view.devices.DevicesViewModel
import com.mci.lara.mobile.view.home.HomeViewModel
import com.mci.lara.mobile.view.login.LoginViewModel
import com.mci.lara.mobile.view.media.MediaViewModel
import com.mci.lara.mobile.view.register.RegisterViewModel
import com.mci.lara.mobile.view.room.RoomViewModel
import com.mci.lara.mobile.view.rooms.RoomsViewModel
import com.mci.lara.mobile.view.settings.SettingsViewModel
import com.mci.lara.mobile.view.splash.SplashViewModel
import org.koin.android.ext.koin.androidApplication
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

/**
Lara
Created by Catalin on 11/24/2020
 **/
object InjectionModule {

    val appModule = module {

        single { SharedPreferencesUtil(androidApplication()) }
        single { CryptographyManager.create(get()) }
        single { LaraDatabase.getInstance(androidApplication()) }

        single { get<LaraDatabase>().featureDao() }
        single { get<LaraDatabase>().houseDao() }
        single { get<LaraDatabase>().userDao() }
        single { get<LaraDatabase>().roomDao() }

        single<LaraApiClient> {
            ClientBuilder.createApiClient(get(), get()).create(LaraApiClient::class.java)
        }
        single<TokenClient> { ClientBuilder.createKeycloakClient().create(TokenClient::class.java) }

        single<TokenRepository> { TokenRepositoryImpl(get(), get()) }
        single<LaraRepository> { LaraRepositoryImpl(get(), get(), get(), get(), get(), get()) }

        single { HeaderInterceptor(get()) }
        single { RefreshInterceptor(get()) }

        viewModel { LoginViewModel(get(), get(), get()) }
        viewModel { RegisterViewModel(get()) }
        viewModel { SplashViewModel() }
        viewModel { RoomsViewModel(get()) }
        viewModel { DevicesViewModel() }
        viewModel { MediaViewModel() }
        viewModel { RoomViewModel(get()) }
        viewModel { HomeViewModel(get()) }
        viewModel { SettingsViewModel(get(), get()) }

    }

}