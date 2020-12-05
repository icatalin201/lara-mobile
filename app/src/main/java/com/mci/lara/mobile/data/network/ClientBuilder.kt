package com.mci.lara.mobile.data.network

import com.mci.lara.mobile.BuildConfig
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

object ClientBuilder {

    fun createApiClient(headerInterceptor: HeaderInterceptor): Retrofit {
        return build(BuildConfig.API_URL, headerInterceptor)
    }

    fun createKeycloakClient(): Retrofit {
        return build(BuildConfig.KEYCLOAK_URL)
    }

    private fun build(url: String, headerInterceptor: HeaderInterceptor? = null): Retrofit {
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
        val okHttpClientBuilder = OkHttpClient
            .Builder()
            .addInterceptor(loggingInterceptor)
        headerInterceptor?.let { okHttpClientBuilder.addInterceptor(it) }
        return Retrofit.Builder()
            .baseUrl(url)
            .client(okHttpClientBuilder.build())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

}