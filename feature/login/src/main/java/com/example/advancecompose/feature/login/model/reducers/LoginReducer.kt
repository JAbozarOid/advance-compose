package com.example.advancecompose.feature.login.model.reducers

import com.example.advancecompose.core.ui.model.IReducer
import com.example.advancecompose.feature.login.model.LoginMutation
import com.example.advancecompose.feature.login.model.LoginRedirection
import com.example.advancecompose.feature.login.model.LoginViewState
import javax.inject.Inject

internal class LoginReducerImpl @Inject constructor() :
    IReducer<LoginMutation, LoginViewState> {
    override fun invoke(
        mutation: LoginMutation,
        currentState: LoginViewState
    ): LoginViewState {
        return currentState.reduceToLoginClicked()
    }
}


private fun LoginViewState.reduceToLoginClicked(): LoginViewState = this.copy(
    showContent = false,
    waitingFor = LoginRedirection.LOGIN,
    loading = false,
    user = null
)
