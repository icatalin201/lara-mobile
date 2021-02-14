package com.mci.lara.mobile.data.remote

import com.mci.lara.mobile.BuildConfig
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

object ClientBuilder {

    fun createApiClient(): Retrofit {
        return build(BuildConfig.API_URL)
    }

    private fun build(url: String): Retrofit {
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
        val okHttpClientBuilder = OkHttpClient
            .Builder()
            .addInterceptor(loggingInterceptor)
        return Retrofit.Builder()
            .baseUrl(url)
            .client(okHttpClientBuilder.build())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

}