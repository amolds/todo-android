package com.olds.swiftandroid.ui.login

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.olds.swiftandroid.api.Todo
import com.olds.swiftandroid.api.TodoRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class LoginViewModel
@Inject constructor(
    private val todoRepository: TodoRepository
) : ViewModel() {
    val todos = mutableStateListOf<Todo>()

    init {

    }
}