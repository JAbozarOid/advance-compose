package com.example.advancecompose.feature.login.di

import com.example.advancecompose.core.ui.di.annotation.ForViewModel
import com.example.advancecompose.core.ui.model.IActionProcessor
import com.example.advancecompose.core.ui.model.IReducer
import com.example.advancecompose.feature.login.model.LoginAction
import com.example.advancecompose.feature.login.model.LoginEvent
import com.example.advancecompose.feature.login.model.LoginMutation
import com.example.advancecompose.feature.login.model.LoginViewState
import com.example.advancecompose.feature.login.model.actionProcessors.CreateAccountActionProcessor
import com.example.advancecompose.feature.login.model.actionProcessors.LoginActionProcessorImpl
import com.example.advancecompose.feature.login.model.reducers.CreateAccountReducer
import com.example.advancecompose.feature.login.model.reducers.LoginReducerImpl
import com.example.advancecompose.feature.login.viewmodel.LoginViewModel
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlin.reflect.KClass

@Module
@InstallIn(SingletonComponent::class)
internal abstract class LoginModule {

    companion object {
        @Provides
        @ForViewModel(LoginViewModel::class)
        fun provideLoginViewModelActionProcessors(
            createAccountActionProcessor: CreateAccountActionProcessor,
            loginActionProcessorImpl: LoginActionProcessorImpl
        ): Map<KClass<out LoginAction>, IActionProcessor<*, LoginMutation, LoginEvent>> =
            hashMapOf(
                (LoginAction.CreateAccountClicked::class to createAccountActionProcessor),
                (LoginAction.LoginClicked::class to loginActionProcessorImpl),
            )

        @Provides
        @ForViewModel(LoginViewModel::class)
        fun provideLoginViewModelReducers(
            loginReducer: LoginReducerImpl,
            createAccountReducer: CreateAccountReducer,
        ): Map<KClass<out LoginMutation>, IReducer<*, LoginViewState>> =
            hashMapOf(
                LoginMutation.LoginClicked::class to loginReducer,
                LoginMutation.UserIsLoggedIn::class to createAccountReducer
            )

    }

}