package com.example.e_commerceapp.service

import okhttp3.Interceptor
import okhttp3.Response

class ApiKeyInterceptor(val apiKey:String):Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest=chain.request()
        val originalUrl=originalRequest.url

        val newUrl=originalUrl.newBuilder()
            .addQueryParameter("api-key",apiKey)
            .build()

        val newRequest=originalRequest.newBuilder()
            .url(newUrl)
            .build()

        return chain.proceed(newRequest)

    }
}