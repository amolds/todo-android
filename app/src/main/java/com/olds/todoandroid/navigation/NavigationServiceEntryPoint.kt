package com.olds.todoandroid.navigation

import dagger.hilt.EntryPoint
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@EntryPoint
@InstallIn(SingletonComponent::class)
interface NavigationServiceEntryPoint {
    fun navigationService(): NavigationService
}
