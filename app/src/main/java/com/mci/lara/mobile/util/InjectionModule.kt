package com.mci.lara.mobile.util

import com.mci.lara.mobile.data.network.*
import com.mci.lara.mobile.data.repository.*
import com.mci.lara.mobile.view.login.LoginViewModel
import com.mci.lara.mobile.view.register.RegisterViewModel
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
        single { ClientBuilder.createApiClient() }

        single { get<Retrofit>().create(UserClient::class.java) }
        single { get<Retrofit>().create(HouseClient::class.java) }
        single { get<Retrofit>().create(FeatureClient::class.java) }
        single { get<Retrofit>().create(RoomClient::class.java) }

        single { ClientBuilder.createKeycloakClient().create(LoginClient::class.java) }

        single { HouseRepository(get()) }
        single { RoomRepository(get()) }
        single { UserRepository(get(), get()) }
        single { FeatureRepository(get()) }
        single { TokenRepository(get()) }

        viewModel { LoginViewModel(get(), get()) }
        viewModel { RegisterViewModel(get()) }
        viewModel { SplashViewModel(get()) }

    }

}