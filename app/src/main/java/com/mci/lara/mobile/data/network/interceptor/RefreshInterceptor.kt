package com.mci.lara.mobile.data.network.interceptor

import com.mci.lara.mobile.data.repository.TokenRepository
import okhttp3.Interceptor
import okhttp3.Response

/**
Lara
Created by catalin.matache on 12/6/2020
 */
class RefreshInterceptor(
    private val tokenRepository: TokenRepository
) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val response = chain.proceed(request)
        val statusCode = response.code
        return if (statusCode == 401) {
            tokenRepository.refreshToken()
            val token = tokenRepository.getToken()
            val newRequest = request.newBuilder()
                .addHeader(
                    "Authorization",
                    "Bearer $token"
                ).build()
            chain.proceed(newRequest)
        } else {
            response
        }
    }

}