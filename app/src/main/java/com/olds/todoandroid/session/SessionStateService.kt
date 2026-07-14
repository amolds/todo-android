package com.olds.todoandroid.session

interface SessionStateService {
    fun getAccessToken(): String?

    fun setAccessToken(token: String)

    fun clearAccessToken()
}
