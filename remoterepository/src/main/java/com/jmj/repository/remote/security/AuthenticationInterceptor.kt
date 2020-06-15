package com.jmj.repository.remote.security

import okhttp3.Interceptor
import okhttp3.Response

class AuthenticationInterceptor(private val authToken:String) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response? {
        val original = chain.request()
        val request = original.newBuilder()
            .header("Authorization", authToken).build()
        return chain.proceed(request)
    }
}