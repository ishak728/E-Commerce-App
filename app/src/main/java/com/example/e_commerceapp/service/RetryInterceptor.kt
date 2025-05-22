package com.example.e_commerceapp.service

import okhttp3.Interceptor
import okhttp3.Response

class RetryInterceptor(val maxRetry: Int):Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        for (i in 1..maxRetry){
            try {
                return chain.proceed(chain.request())
            }catch (e:Exception){
                throw e
            }
        }

        throw RuntimeException("request failed")
    }
}