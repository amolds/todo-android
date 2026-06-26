package com.olds.swiftandroid.api

import retrofit2.http.GET

interface TodoApi {
    @GET("todos")
    suspend fun getTodos(): List<Todo>
}
