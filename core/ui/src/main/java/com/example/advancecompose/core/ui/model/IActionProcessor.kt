package com.example.advancecompose.core.ui.model

import com.example.advancecompose.core.ui.entity.DisplayedError
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.FlowCollector
import kotlinx.coroutines.flow.flow

// single abstract method(sam) or functional interface : An interface with only one abstract method
// Fun interfaces must have exactly one abstract method
interface IActionProcessor<in Action, Mutation, Event> {

    suspend fun onAction(action: Action): ActionResult<Mutation, Event>

    fun onError(throwable: Throwable): ActionResult<Mutation, Event> =
        ActionResult.Error(
            showError = DisplayedError.SnackBarError(
                message = throwable.message ?: "Unknown Error"
            )
        )
}

sealed class ActionResult<Mutation, Event>(
    val success: Flow<Pair<Mutation?, Event?>>? = null,
    val error: DisplayedError? = null
) {

    class Success<Mutation, Event>(vararg results: Pair<Mutation?, Event?>) :
        ActionResult<Mutation, Event>(
            success = flow {
                results.forEach {
                    emit(it)
                }
            })

    class Error<Mutation, Event>(showError: DisplayedError) :
        ActionResult<Mutation, Event>(error = showError)


    class ErrorWithData<Mutation,Event>(
        vararg results : Pair<Mutation?,Event?>,
        showError: DisplayedError?
    ) : ActionResult<Mutation,Event>(
        success = flow { results.forEach { emit(it) } },
        error = showError
    )
}

fun<Mutation,Event> IActionProcessor<*,*,*>.resultAsPair(
    mutation: Mutation?,
    event: Event?
) = Pair(mutation,event)
