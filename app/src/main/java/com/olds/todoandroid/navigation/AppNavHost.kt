package com.olds.todoandroid.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.EntryPointAccessors
import com.olds.todoandroid.ui.login.LoginScreen
import com.olds.todoandroid.ui.todoList.TodoListScreen

@Composable
fun AppNavHost() {
    val navController = rememberNavController()
    val context = LocalContext.current.applicationContext
    val navigationService = remember(context) {
        EntryPointAccessors.fromApplication(
            context,
            NavigationServiceEntryPoint::class.java,
        ).navigationService()
    }

    LaunchedEffect(navController, navigationService) {
        navigationService.commands.collect { command ->
            when (command) {
                is NavigationCommand.Navigate -> {
                    navController.navigate(command.route) {
                        launchSingleTop = command.launchSingleTop
                        command.popUpTo?.let { target ->
                            popUpTo(target) {
                                inclusive = command.inclusive
                            }
                        }
                    }
                }
                NavigationCommand.Back -> navController.popBackStack()
            }
        }
    }

    NavHost(
        navController = navController,
        startDestination = AppDestination.AuthGraph,
    ) {
        navigation(
            startDestination = AppDestination.Login,
            route = AppDestination.AuthGraph,
        ) {
            composable(AppDestination.Login) {
                LoginScreen()
            }
        }

        navigation(
            startDestination = AppDestination.TodoList,
            route = AppDestination.MainGraph,
        ) {
            composable(AppDestination.TodoList) {
                TodoListScreen()
            }
        }
    }
}
