package com.mci.lara.mobile.data.network

import com.mci.lara.mobile.data.repository.TokenRepository
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response

/**
Lara
Created by Catalin on 12/5/2020
 **/
class HeaderInterceptor(
    private val tokenRepository: TokenRepository
) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val token = tokenRepository.get()
        val request: Request = chain.request()
            .newBuilder()
            .addHeader(
                "Authorization",
                "Bearer $token"
            ).build()
        return chain.proceed(request)
    }

}