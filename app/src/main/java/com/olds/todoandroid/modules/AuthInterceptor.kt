package com.olds.todoandroid.modules

import com.olds.todoandroid.session.SessionStateService
import javax.inject.Inject
import okhttp3.Interceptor
import okhttp3.Response

class AuthInterceptor @Inject constructor(
    private val sessionStateService: SessionStateService,
) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val token = sessionStateService.getAccessToken()
        val request = if (token.isNullOrBlank()) {
            chain.request()
        } else {
            chain.request().newBuilder()
                .addHeader("Authorization", "Bearer $token")
                .build()
        }
        return chain.proceed(request)
    }
}
