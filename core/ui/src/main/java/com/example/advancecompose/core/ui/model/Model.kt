package com.example.advancecompose.core.ui.model

import com.example.advancecompose.core.ui.dispatcher.DispatcherProvider
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlin.reflect.KClass

class Model<State, Action : IAction, Mutation : IMutation, Event>(
    private val actionProcessors: Map<KClass<out Action>, IActionProcessor<*, Mutation, Event>>,
    private val reducers: Map<KClass<out Mutation>, IReducer<*, State>>,
    private val coroutineScope: CoroutineScope,
    private val dispatcherProvider: DispatcherProvider,
    private val viewMutableStateFlow: MutableStateFlow<State>,
    private val eventChannel: Channel<Event>
) {

    val viewStateFlow: Flow<State> = viewMutableStateFlow
    val eventFlow: Flow<Event> = eventChannel.receiveAsFlow()

    fun process(action: Action) {
        coroutineScope.launch(dispatcherProvider.io) {
            val key = actionProcessors.keys.find { it == action::class }
            val processor = actionProcessors[key]
            (processor as? IActionProcessor<Action, Mutation, Event>)?.invoke(action)
                ?.collect { (mutation, event) ->
                    mutation?.let {
                        handleMutation(it)
                    }
                    event?.let {
                        eventChannel.trySend(it)
                    }
                }

        }
    }

    private fun handleMutation(mutation: Mutation) {
        reducers.filter { it.key == mutation::class }.forEach { reducer ->
            viewMutableStateFlow.update { currentState ->
                (reducer.value as IReducer<Mutation, State>).invoke(
                    mutation,
                    currentState
                )
            }
        }
    }
}