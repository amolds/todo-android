package com.olds.swiftandroid.api

import javax.inject.Inject

class TodoRepository
    @Inject constructor(
        private val todoApi: TodoApi
    ) {
        suspend fun getTodos(): List<Todo> {
            return todoApi.getTodos()
        }
    }
