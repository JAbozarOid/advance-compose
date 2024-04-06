package com.example.advancecompose.feature.login.model.actionProcessors

import com.example.advancecompose.core.ui.model.ActionResult
import com.example.advancecompose.core.ui.model.IActionProcessor
import com.example.advancecompose.core.ui.model.resultAsPair
import com.example.advancecompose.feature.login.model.LoginAction
import com.example.advancecompose.feature.login.model.LoginEvent
import com.example.advancecompose.feature.login.model.LoginMutation
import javax.inject.Inject


internal class CreateAccountActionProcessor @Inject constructor(
) :
    IActionProcessor<LoginAction.CreateAccountClicked, LoginMutation, LoginEvent> {

    override suspend fun onAction(action: LoginAction.CreateAccountClicked): ActionResult<LoginMutation, LoginEvent> {
        resultAsPair(null, LoginEvent.CreateAccountEvent("Account Created"))
        return ActionResult.Success()
    }

}
