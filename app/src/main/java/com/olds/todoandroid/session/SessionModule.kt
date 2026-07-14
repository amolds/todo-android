package com.olds.todoandroid.session

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class SessionModule {
    @Binds
    abstract fun bindSessionStateService(
        impl: SessionStateServiceImpl,
    ): SessionStateService
}
