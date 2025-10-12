package com.example.advancecompose.model

import com.example.advancecompose.core.ui.model.ActionResult
import com.example.advancecompose.core.ui.model.IActionProcessor
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.FlowCollector
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

internal class MainActionProcessorImpl @Inject constructor() :
    IActionProcessor<MainAction.ViewCreated, MainMutation, MainEvent> {
    override suspend fun onAction(action: MainAction.ViewCreated): ActionResult<MainMutation, MainEvent> {
        TODO("Not yet implemented")
    }

}

private suspend fun FlowCollector<Pair<MainMutation?, MainEvent?>>.getStartupData() {
    delay(1000)
    emit(MainMutation.StartUpDataFetched to null)
}