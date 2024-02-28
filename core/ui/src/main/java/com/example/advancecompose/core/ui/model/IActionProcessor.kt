package com.example.advancecompose.core.ui.model

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.FlowCollector

// single abstract method(sam) or functional interface : An interface with only one abstract method
fun interface IActionProcessor<Action, Mutation, Event> {
    fun invoke(action: Action): Flow<Pair<Mutation?, Event?>>
}

suspend fun <Mutation : IMutation, Event : IEvent> FlowCollector<Pair<Mutation?, Event?>>.emitAsPair(
    mutation: Mutation?,
    event: Event?
) {
    emit(Pair(mutation, event))
}
