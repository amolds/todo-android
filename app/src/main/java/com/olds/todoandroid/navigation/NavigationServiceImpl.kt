package com.olds.todoandroid.navigation

import javax.inject.Inject
import javax.inject.Singleton
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow

@Singleton
class NavigationServiceImpl @Inject constructor() : NavigationService {
    private val _commands = MutableSharedFlow<NavigationCommand>(
        extraBufferCapacity = 1,
        onBufferOverflow = BufferOverflow.DROP_OLDEST,
    )

    override val commands: SharedFlow<NavigationCommand> = _commands.asSharedFlow()

    override fun navigate(
        route: String,
        popUpTo: String?,
        inclusive: Boolean,
        launchSingleTop: Boolean,
    ) {
        _commands.tryEmit(
            NavigationCommand.Navigate(
                route = route,
                popUpTo = popUpTo,
                inclusive = inclusive,
                launchSingleTop = launchSingleTop,
            ),
        )
    }

    override fun back() {
        _commands.tryEmit(NavigationCommand.Back)
    }
}
