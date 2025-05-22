package com.example.e_commerceapp.service

import okhttp3.CacheControl
import okhttp3.Interceptor
import okhttp3.Response
import java.util.concurrent.TimeUnit

class NetworkCacheInterceptor:Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val response=chain.proceed(chain.request())

        val casheControl=CacheControl.Builder()
            .maxAge(5,TimeUnit.MINUTES)
            .build()
        return response.newBuilder()
            .addHeader("Cache-Control",casheControl.toString())
            .build()
    }
}