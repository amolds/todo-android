package com.olds.todoandroid.api

import kotlinx.serialization.Serializable

@Serializable
data class LoginResponse(
    val token: String
)
