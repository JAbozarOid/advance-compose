package com.example.advancecompose.model

import com.example.advancecompose.core.ui.model.IActionProcessor
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.FlowCollector
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

internal class MainActionProcessorImpl @Inject constructor() :
    IActionProcessor<MainAction.ViewCreated, MainMutation, MainEvent> {
    override fun invoke(action: MainAction.ViewCreated): Flow<Pair<MainMutation?, MainEvent?>> {
        return flow { getStartupData() }
    }
}

private suspend fun FlowCollector<Pair<MainMutation?, MainEvent?>>.getStartupData() {
    delay(1000)
    emit(MainMutation.StartUpDataFetched to null)
}