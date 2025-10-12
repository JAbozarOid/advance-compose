package com.example.advancecompose.model

import com.example.advancecompose.core.ui.model.IViewState

internal data class MainViewState(
    val showContent: Boolean = true,
    val waitingFor: LoginRedirection = LoginRedirection.NOTHING,
    val loading: Boolean = false,
) : IViewState

internal enum class LoginRedirection {
    LOGIN, CREATE_ACCOUNT, NOTHING
}