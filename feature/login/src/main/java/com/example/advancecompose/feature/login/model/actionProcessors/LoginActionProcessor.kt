package com.example.advancecompose.feature.login.model.actionProcessors

import com.example.advancecompose.core.domain.usecase.user.GetUserUseCase
import com.example.advancecompose.core.ui.model.ActionResult
import com.example.advancecompose.core.ui.model.IActionProcessor
import com.example.advancecompose.feature.login.model.LoginAction
import com.example.advancecompose.feature.login.model.LoginEvent
import com.example.advancecompose.feature.login.model.LoginMutation
import javax.inject.Inject

internal class LoginActionProcessorImpl @Inject constructor(
    private val getUserUseCase: GetUserUseCase
) :
    IActionProcessor<LoginAction.LoginClicked, LoginMutation, LoginEvent> {

    override suspend fun onAction(action: LoginAction.LoginClicked): ActionResult<LoginMutation, LoginEvent> {
        TODO("Not yet implemented")
    }

}

