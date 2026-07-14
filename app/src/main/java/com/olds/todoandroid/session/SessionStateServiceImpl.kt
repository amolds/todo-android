package com.olds.todoandroid.session

import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SessionStateServiceImpl @Inject constructor() : SessionStateService {
    @Volatile
    private var accessToken: String? = null

    override fun getAccessToken(): String? = accessToken

    override fun setAccessToken(token: String) {
        accessToken = token
    }

    override fun clearAccessToken() {
        accessToken = null
    }
}
