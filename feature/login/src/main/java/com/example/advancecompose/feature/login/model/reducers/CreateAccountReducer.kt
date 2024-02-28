package com.example.advancecompose.feature.login.model.reducers

import com.example.advancecompose.core.ui.model.IReducer
import com.example.advancecompose.feature.login.entity.User
import com.example.advancecompose.feature.login.model.LoginMutation
import com.example.advancecompose.feature.login.model.LoginRedirection
import com.example.advancecompose.feature.login.model.LoginViewState
import javax.inject.Inject


internal class CreateAccountReducer @Inject constructor() :
    IReducer<LoginMutation.UserIsLoggedIn, LoginViewState> {
    override fun invoke(mutation: LoginMutation.UserIsLoggedIn, currentState: LoginViewState): LoginViewState {
        return currentState.reduceToUserIsLoggedIn(mutation.user)
    }
}


private fun LoginViewState.reduceToLoginClicked(): LoginViewState = this.copy(
    showContent = false,
    waitingFor = LoginRedirection.LOGIN,
    loading = false,
    user = null
)

private fun LoginViewState.reduceToUserIsLoggedIn(user: User): LoginViewState = this.copy(
    showContent = true,
    waitingFor = LoginRedirection.NOTHING,
    loading = false,
    user = user
)