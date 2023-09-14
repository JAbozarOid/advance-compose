package com.example.core.ui

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.launch

interface ViewEvent
interface ViewState
interface ViewSideEffect
abstract class BaseViewModel<EVENT : ViewEvent, EFFECT : ViewSideEffect, STATE : ViewState>(
    private val dispatcher: CoroutineDispatcher = Dispatchers.Main
) :
    ViewModel() {


    // event : work with the view
    private val _event: MutableSharedFlow<EVENT> by lazy { MutableSharedFlow() }
    abstract fun handleEvent(event: EVENT)
    fun setEvent(event: EVENT) {
        viewModelScope.launch(dispatcher) {
            _event.emit(event)
        }
    }

    /**
     * this method will be run when setEvent(event : EVENT) is called
     */
    private fun collectEvent() {
        viewModelScope.launch(dispatcher) {
            _event.collect {
                handleEvent(it)
            }
        }
    }

    /**
     * when the view model is initialized this method is called
     */
    init {
        collectEvent()
    }

    //effect : is the result of event -> it will be handled in the screen
    // effect will be collect in screen side
    private val _effect: MutableSharedFlow<EFFECT> by lazy { MutableSharedFlow() }
    val effect = _effect

    // this method will be called by all of the view models classes
    protected fun setEffect(effectHandler: () -> EFFECT) {
        viewModelScope.launch(dispatcher) {
            _effect.emit(effectHandler())
        }
    }


    // state : When the app launches, when navigate from one screen to another
    abstract fun setInitialState(): STATE
    private val initialState by lazy { setInitialState() }
    private val _uiState = mutableStateOf(initialState)
    private val state get() = _uiState

    protected fun setState(stateHandler: STATE.() -> STATE) {
        _uiState.value = state.value.stateHandler()
    }


}