package com.olds.swiftandroid.api

import androidx.compose.foundation.MutatePriority
import retrofit2.http.GET

data class Todo(
    val id: String,
    val username: String,
    val title: String,
    val description: String,
    val priority: String,
    val completed : Boolean,
)
