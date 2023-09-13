package com.example.core.di

import com.example.core.util.DefaultDispatcherProvider
import com.example.core.util.DispatcherProvider
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object CoreModule {

    /**
     * using in out view model and also help for unit testing
     */
    @Singleton
    @Provides
    fun provideDispatcher() : DispatcherProvider = DefaultDispatcherProvider()
}