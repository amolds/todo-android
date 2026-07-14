package com.olds.todoandroid.navigation

sealed interface NavigationCommand {
    data class Navigate(
        val route: String,
        val popUpTo: String? = null,
        val inclusive: Boolean = false,
        val launchSingleTop: Boolean = true,
    ) : NavigationCommand

    data object Back : NavigationCommand
}
