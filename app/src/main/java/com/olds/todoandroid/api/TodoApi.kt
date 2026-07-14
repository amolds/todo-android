package com.olds.todoandroid.api

import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface TodoApi {
    @POST("auth/login")
    suspend fun login(@Body request: LoginRequest): LoginResponse

    @GET("todos")
    suspend fun getTodos(): List<Todo>
}
