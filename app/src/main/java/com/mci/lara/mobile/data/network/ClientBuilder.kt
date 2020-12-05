package com.mci.lara.mobile.data.network

import com.mci.lara.mobile.BuildConfig
import com.mci.lara.mobile.data.network.interceptor.HeaderInterceptor
import com.mci.lara.mobile.data.network.interceptor.RefreshInterceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

object ClientBuilder {

    fun createApiClient(
        headerInterceptor: HeaderInterceptor,
        refreshInterceptor: RefreshInterceptor
    ): Retrofit {
        return build(BuildConfig.API_URL, headerInterceptor, refreshInterceptor)
    }

    fun createKeycloakClient(): Retrofit {
        return build(BuildConfig.KEYCLOAK_URL)
    }

    private fun build(
        url: String,
        headerInterceptor: HeaderInterceptor? = null,
        refreshInterceptor: RefreshInterceptor? = null
    ): Retrofit {
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
        val okHttpClientBuilder = OkHttpClient
            .Builder()
            .addInterceptor(loggingInterceptor)
        headerInterceptor?.let { okHttpClientBuilder.addInterceptor(it) }
        refreshInterceptor?.let { okHttpClientBuilder.addInterceptor(it) }
        return Retrofit.Builder()
            .baseUrl(url)
            .client(okHttpClientBuilder.build())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

}