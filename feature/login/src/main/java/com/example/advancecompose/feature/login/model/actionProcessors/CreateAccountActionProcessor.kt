package com.example.advancecompose.feature.login.model.actionProcessors

import com.example.advancecompose.core.domain.model.user.UserModel
import com.example.advancecompose.core.domain.usecase.user.GetUserUseCase
import com.example.advancecompose.core.ui.model.IActionProcessor
import com.example.advancecompose.core.ui.model.emitAsPair
import com.example.advancecompose.feature.login.entity.User
import com.example.advancecompose.feature.login.model.LoginAction
import com.example.advancecompose.feature.login.model.LoginEvent
import com.example.advancecompose.feature.login.model.LoginMutation
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.FlowCollector
import kotlinx.coroutines.flow.flow
import javax.inject.Inject


internal class CreateAccountActionProcessor @Inject constructor(
    private val getUserUseCase: GetUserUseCase
) :
    IActionProcessor<LoginAction.CreateAccountClicked, LoginMutation, LoginEvent> {
    override fun invoke(action: LoginAction.CreateAccountClicked): Flow<Pair<LoginMutation?, LoginEvent?>> {
        return flow {
            mutateToLoginClicked()
        }
    }

    private suspend fun FlowCollector<Pair<LoginMutation?, LoginEvent?>>.mutateToLoginClicked() {
        val result = getUserUseCase.apply { executeDispatcher = Dispatchers.IO }.invoke(null)
        emitAsPair(
            null,
            LoginEvent.CreateAccountEvent(result.toViewModel())
        )
    }

    private fun UserModel.toViewModel(): User {
        return User(name = this.name.value)
    }

}
