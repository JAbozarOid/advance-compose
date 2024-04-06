package com.example.advancecompose.feature.login.model

import com.example.advancecompose.core.ui.model.IAction
import com.example.advancecompose.core.ui.model.IEvent
import com.example.advancecompose.core.ui.model.IMutation
import com.example.advancecompose.core.ui.model.IViewState
import com.example.advancecompose.feature.login.entity.User

internal sealed interface LoginEvent : IEvent {
    data class CreateAccountEvent(val message: String) : LoginEvent
}

internal sealed interface LoginMutation : IMutation {
    data object LoginClicked : LoginMutation
    data class UserIsLoggedIn(val user: User) : LoginMutation
}

internal sealed interface LoginAction : IAction {
    data object LoginClicked : LoginAction
    data object CreateAccountClicked : LoginAction
}

internal data class LoginViewState(
    val showContent: Boolean = true,
    val waitingFor: LoginRedirection = LoginRedirection.NOTHING,
    val loading: Boolean = false,
    val user: User? = null
) : IViewState

internal enum class LoginRedirection {
    LOGIN, CREATE_ACCOUNT, NOTHING
}