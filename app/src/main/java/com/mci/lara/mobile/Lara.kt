package com.mci.lara.mobile

import android.app.Application
import com.mci.lara.mobile.data.Injection
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

/**
Lara
Created by Catalin on 11/24/2020
 **/
class Lara : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@Lara)
            modules(Injection.appModule)
        }
    }

}