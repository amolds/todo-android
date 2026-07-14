package com.olds.todoandroid.api

import com.olds.todoandroid.session.SessionStateService
import javax.inject.Inject

class TodoRepository
    @Inject constructor(
        private val todoApi: TodoApi,
        private val sessionStateService: SessionStateService,
    ) {
        suspend fun login(username: String, password: String): String {
            val request = LoginRequest(username, password)
            val response = todoApi.login(request)
            sessionStateService.setAccessToken(response.token)
            return response.token
        }

        fun logout() {
            sessionStateService.clearAccessToken()
        }

        suspend fun getTodos(): List<Todo> {
            return todoApi.getTodos()
        }
    }
