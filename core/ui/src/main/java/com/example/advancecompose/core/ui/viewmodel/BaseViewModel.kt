package com.example.advancecompose.core.ui.viewmodel

import androidx.lifecycle.ViewModel
import com.example.advancecompose.core.ui.dispatcher.DispatcherProvider
import com.example.advancecompose.core.ui.entity.DisplayedError
import com.example.advancecompose.core.ui.model.IAction
import com.example.advancecompose.core.ui.model.IActionProcessor
import com.example.advancecompose.core.ui.model.IMutation
import com.example.advancecompose.core.ui.model.IReducer
import com.example.advancecompose.core.ui.model.Model
import com.example.advancecompose.core.ui.model.model
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.FlowCollector
import kotlinx.coroutines.flow.flatMapConcat
import kotlinx.coroutines.flow.flow
import kotlin.reflect.KClass

abstract class BaseViewModel<State, Action : IAction, Mutation : IMutation, Event>(
    actionProcessors: Map<KClass<out Action>, IActionProcessor<*, Mutation, Event>> = mapOf(),
    reducers: Map<KClass<out Mutation>, IReducer<*, State>> = mapOf(),
    initialState: State,
    dispatcherProvider: DispatcherProvider
) :
    ViewModel() {

    protected val model: Model<State, Action, Mutation, Event> by model(
        actionProcessors = actionProcessors,
        reducers = reducers,
        initialState = initialState,
        dispatcherProvider = dispatcherProvider
    )

    val errorFlow : Flow<DisplayedError> = model.errorFlow
    val viewStateFlow: Flow<State>
        get() = model.viewStateFlow.flatMapConcat {
            return@flatMapConcat flow { manipulateState(it) }
        }

    val eventFlow: Flow<Event> get() = model.eventFlow

    fun process(action: Action) = model.process(action)
    open suspend fun FlowCollector<State>.manipulateState(state: State) {
        emit(state)
    }
}