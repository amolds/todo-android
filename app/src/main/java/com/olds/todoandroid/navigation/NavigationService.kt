package com.olds.todoandroid.navigation

import kotlinx.coroutines.flow.SharedFlow

interface NavigationService {
    val commands: SharedFlow<NavigationCommand>

    fun navigate(
        route: String,
        popUpTo: String? = null,
        inclusive: Boolean = false,
        launchSingleTop: Boolean = true,
    )

    fun back()
}
