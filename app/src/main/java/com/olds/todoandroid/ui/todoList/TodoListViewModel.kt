package com.olds.todoandroid.ui.todoList

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.olds.todoandroid.api.Todo
import com.olds.todoandroid.api.TodoRepository
import com.olds.todoandroid.navigation.AppDestination
import com.olds.todoandroid.navigation.NavigationService
import dagger.hilt.android.lifecycle.HiltViewModel
import java.util.concurrent.CancellationException
import kotlinx.coroutines.launch
import javax.inject.Inject

data class TodoListUiState(
    val isLoading: Boolean = false,
    val errorMessage: String? = null,
)

@HiltViewModel
class TodoListViewModel
    @Inject constructor(
        private val todoRepository: TodoRepository,
        private val navigationService: NavigationService,
    ) : ViewModel() {
        val todos = mutableStateListOf<Todo>()
        var uiState by mutableStateOf(TodoListUiState(isLoading = true))
            private set

        init {
            viewModelScope.launch {
                uiState = uiState.copy(isLoading = true, errorMessage = null)
                try {
                    todos.clear()
                    todos.addAll(todoRepository.getTodos())
                    uiState = uiState.copy(isLoading = false)
                } catch (e: CancellationException) {
                    throw e
                } catch (e: Exception) {
                    uiState = uiState.copy(
                        isLoading = false,
                        errorMessage = e.message ?: "Unable to load todos.",
                    )
                }
            }
        }

        fun onLogoutClicked() {
            viewModelScope.launch {
                todoRepository.logout()
                navigationService.navigate(
                    route = AppDestination.AuthGraph,
                    popUpTo = AppDestination.MainGraph,
                    inclusive = true,
                )
            }
        }
    }