package com.example.advancecompose.di

import com.example.advancecompose.core.ui.di.annotation.ForViewModel
import com.example.advancecompose.core.ui.model.IActionProcessor
import com.example.advancecompose.core.ui.model.IReducer
import com.example.advancecompose.model.MainAction
import com.example.advancecompose.model.MainActionProcessorImpl
import com.example.advancecompose.model.MainEvent
import com.example.advancecompose.model.MainMutation
import com.example.advancecompose.model.MainReducerImpl
import com.example.advancecompose.model.MainViewState
import com.example.advancecompose.viewmodel.MainViewModel
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlin.reflect.KClass

@Module
@InstallIn(SingletonComponent::class)
internal abstract class MainModule {

    companion object {
        @Provides
        @ForViewModel(MainViewModel::class)
        fun provideMainViewModelActionProcessors(
            mainActionProcessor: MainActionProcessorImpl
        ): Map<KClass<out MainAction>, IActionProcessor<*, MainMutation, MainEvent>> =
            hashMapOf(
                (MainAction.ViewCreated::class to mainActionProcessor)
            )

        @Provides
        @ForViewModel(MainViewModel::class)
        fun provideMainViewModelReducers(
            mainReducerImpl: MainReducerImpl
        ): Map<KClass<out MainMutation>, IReducer<*, MainViewState>> =
            hashMapOf(
                MainMutation.StartUpDataFetched::class to mainReducerImpl
            )

    }
}