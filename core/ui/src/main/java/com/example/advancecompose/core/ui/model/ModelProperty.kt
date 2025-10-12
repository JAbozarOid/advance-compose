package com.example.advancecompose.core.ui.model

import androidx.lifecycle.viewModelScope
import com.example.advancecompose.core.ui.dispatcher.DispatcherProvider
import com.example.advancecompose.core.ui.dispatcher.StandardDispatcherProvider
import com.example.advancecompose.core.ui.entity.DisplayedError
import com.example.advancecompose.core.ui.viewmodel.BaseViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlin.properties.ReadOnlyProperty
import kotlin.reflect.KClass
import kotlin.reflect.KProperty


fun <State, Action : IAction, Mutation : IMutation, Event> BaseViewModel<State, Action, Mutation, Event>.model(
    actionProcessors: Map<KClass<out Action>, IActionProcessor<*, Mutation, Event>>,
    reducers: Map<KClass<out Mutation>, IReducer<*, State>>,
    initialState: State,
    dispatcherProvider: DispatcherProvider
) = ModelProperty(
    viewModel = this,
    actionProcessors = actionProcessors,
    reducers = reducers,
    dispatcherProvider = dispatcherProvider,
    viewMutableStateFlow = MutableStateFlow(initialState),
    eventChannel = Channel(Channel.BUFFERED),
    errorChannel = Channel(Channel.BUFFERED)
)


class ModelProperty<State, Action : IAction, Mutation : IMutation, Event>(
    private val viewModel: BaseViewModel<State, Action, Mutation, Event>,
    private val actionProcessors: Map<KClass<out Action>, IActionProcessor<*, Mutation, Event>>,
    private val reducers: Map<KClass<out Mutation>, IReducer<*, State>>,
    dispatcherProvider: DispatcherProvider,
    private val viewMutableStateFlow: MutableStateFlow<State>,
    private val eventChannel: Channel<Event>,
    private val errorChannel: Channel<DisplayedError>
) : ReadOnlyProperty<Any, Model<State, Action, Mutation, Event>> {

    val dispatcherProvider = StandardDispatcherProvider()

    override fun getValue(
        thisRef: Any,
        property: KProperty<*>
    ): Model<State, Action, Mutation, Event> =
        Model(
            _actionProcessors = actionProcessors,
            _reducers = reducers,
            _coroutineScope = viewModel.viewModelScope,
            _dispatcherProvider = dispatcherProvider,
            _viewMutableStateFlow = viewMutableStateFlow,
            _eventChannel = eventChannel,
            _errorChannel = errorChannel
        )

}