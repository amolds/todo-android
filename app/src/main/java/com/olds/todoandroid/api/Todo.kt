package com.olds.todoandroid.api

import kotlinx.serialization.Serializable

@Serializable
data class Todo(
    val id: String,
    val username: String,
    val title: String,
    val description: String,
    val priority: String,
    val completed: Boolean,
)
