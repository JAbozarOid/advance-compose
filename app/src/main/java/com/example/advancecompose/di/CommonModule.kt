package com.example.advancecompose.di

import com.example.advancecompose.core.ui.dispatcher.DispatcherProvider
import com.example.advancecompose.core.ui.dispatcher.StandardDispatcherProvider
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class CommonModule {

    @Provides
    fun provideStandardDispatcherProvider(): DispatcherProvider = StandardDispatcherProvider()

}