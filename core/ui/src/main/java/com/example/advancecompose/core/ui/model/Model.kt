package com.example.advancecompose.core.ui.model

import com.example.advancecompose.core.ui.dispatcher.DispatcherProvider
import com.example.advancecompose.core.ui.entity.DisplayedError
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext
import kotlin.reflect.KClass

class Model<State, Action : IAction, Mutation : IMutation, Event>(
    private val _actionProcessors: Map<KClass<out Action>, IActionProcessor<*, Mutation, Event>>,
    private val _reducers: Map<KClass<out Mutation>, IReducer<*, State>>,
    private val _coroutineScope: CoroutineScope,
    private val _dispatcherProvider: DispatcherProvider,
    private val _viewMutableStateFlow: MutableStateFlow<State>,
    private val _eventChannel: Channel<Event>,
    private val _errorChannel: Channel<DisplayedError>
) {

    val viewStateFlow: Flow<State> = _viewMutableStateFlow
    val eventFlow: Flow<Event> = _eventChannel.receiveAsFlow()
    val errorFlow: Flow<DisplayedError> = _errorChannel.receiveAsFlow()

    fun process(action: Action) {

        val actionProcessor: IActionProcessor<Action, Mutation, Event>? =
            getActionProcessor(action)

        actionProcessor?.let { safeActionProcessor ->
            _coroutineScope.launch(
                getDefaultCoroutineContext(actionProcessor)
            ) {
                safeActionProcessor.onAction(action).success?.collect(this@Model::onActionResult)
            }
        }
    }

    private fun onActionResult(it: Pair<Mutation?, Event?>) {
        it.first?.let {
            mutate(it)
        }
        it.second?.let {
            sendEvent(it)
        }
    }

    private fun sendEvent(it: Event) = _eventChannel.trySend(it)

    private fun getDefaultCoroutineContext(actionProcessor: IActionProcessor<Action, Mutation, Event>) =
        _dispatcherProvider.io + getDefaultExceptionHandler(actionProcessor)


    private fun getDefaultExceptionHandler(actionProcessor: IActionProcessor<Action, Mutation, Event>) =
        CoroutineExceptionHandler { _, throwable ->
            val errorResult = actionProcessor.onError(throwable)
            errorResult.success?.let { resultFlow ->
                _coroutineScope.launch(_dispatcherProvider.io + getEmptyExceptionHandler()) {
                    resultFlow.collectLatest { this@Model::onActionResult }
                }
            }
            errorResult.error?.let { sendError(it) }
        }


    private fun sendError(it: DisplayedError) {
        _errorChannel.trySend(it)
    }

    private fun getEmptyExceptionHandler() =
        CoroutineExceptionHandler { _, throwable -> throwable.printStackTrace() }

    private fun getActionProcessor(action: Action): IActionProcessor<Action, Mutation, Event>? {
        val actionProcessor = _actionProcessors[_actionProcessors.keys.find {
            it == action::class
        }] as IActionProcessor<Action, Mutation, Event>

        return actionProcessor
    }


    private fun mutate(mutation: Mutation) {
        _reducers.filter { it.key == mutation::class }.forEach { reducer ->
            _viewMutableStateFlow.update { currentState ->
                (reducer.value as IReducer<Mutation, State>).invoke(
                    mutation, currentState
                )
            }
        }
    }

}