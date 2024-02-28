package com.example.advancecompose.feature.login.viewmodel

import com.example.advancecompose.core.ui.di.annotation.ForViewModel
import com.example.advancecompose.core.ui.model.IActionProcessor
import com.example.advancecompose.core.ui.model.IReducer
import com.example.advancecompose.core.ui.viewmodel.BaseViewModel
import com.example.advancecompose.feature.login.model.LoginAction
import com.example.advancecompose.feature.login.model.LoginEvent
import com.example.advancecompose.feature.login.model.LoginMutation
import com.example.advancecompose.feature.login.model.LoginViewState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlin.reflect.KClass

@HiltViewModel
internal class LoginViewModel @Inject constructor(
    @ForViewModel(LoginViewModel::class)
    actionProcessors: Map<KClass<out LoginAction>, @JvmSuppressWildcards IActionProcessor<*, LoginMutation, LoginEvent>>,
    @ForViewModel(LoginViewModel::class)
    reducers: Map<KClass<out LoginMutation>, @JvmSuppressWildcards IReducer<*, LoginViewState>>,
) : BaseViewModel<LoginViewState, LoginAction, LoginMutation, LoginEvent>(
    actionProcessors = actionProcessors,
    reducers = reducers,
    initialState = LoginViewState()
)