package com.example.advancecompose.core.ui.model

import androidx.lifecycle.viewModelScope
import com.example.advancecompose.core.ui.dispatcher.StandardDispatcherProvider
import com.example.advancecompose.core.ui.viewmodel.BaseViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlin.properties.ReadOnlyProperty
import kotlin.reflect.KClass
import kotlin.reflect.KProperty


fun <State, Action : IAction, Mutation : IMutation, Event> BaseViewModel<State, Action, Mutation, Event>.model(
    actionProcessors: Map<KClass<out Action>, IActionProcessor<*, Mutation, Event>>,
    reducers: Map<KClass<out Mutation>, IReducer<*, State>>,
    initialState: State
) = ModelProperty(
    viewModel = this,
    actionProcessors = actionProcessors,
    reducers = reducers,
//    dispatcherProvider = dispatcherProvider,
    viewMutableStateFlow = MutableStateFlow(initialState),
    eventChannel = Channel(Channel.BUFFERED)
)


class ModelProperty<State, Action : IAction, Mutation : IMutation, Event>(
    private val viewModel: BaseViewModel<State, Action, Mutation, Event>,
    private val actionProcessors: Map<KClass<out Action>, IActionProcessor<*, Mutation, Event>>,
    private val reducers: Map<KClass<out Mutation>, IReducer<*, State>>,
    private val viewMutableStateFlow: MutableStateFlow<State>,
    private val eventChannel: Channel<Event>,
) :  ReadOnlyProperty<Any, Model<State, Action, Mutation, Event>> {

    val dispatcherProvider = StandardDispatcherProvider()

    override fun getValue(
        thisRef: Any,
        property: KProperty<*>
    ): Model<State, Action, Mutation, Event> =
        Model(
            actionProcessors = actionProcessors,
            reducers = reducers,
            coroutineScope = viewModel.viewModelScope,
            dispatcherProvider = dispatcherProvider,
            viewMutableStateFlow = viewMutableStateFlow,
            eventChannel = eventChannel
        )

}