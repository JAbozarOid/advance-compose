package com.example.advancecompose.feature.interview.kotlinFlow

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.asCoroutineDispatcher
import kotlinx.coroutines.async
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import java.util.concurrent.Executors

/**
 * both state and shared flow are hot flows
 * difference :
 *      1- StateFlow :
 *          - needs init value and emit it as soon as the collector start collecting
 *          - only emit the last known value
 *          - it hase value property, it can get the value without collecting
 *          - it does not emit consecutive repeated values
 *          - Similar to LiveData except for the Lifecycle awareness, should use repeatOnLifecycle scope with StateFlow to add the Lifecycle awareness
 *      2- SharedFlow :
 *          - doesn't need init value so doesn't emit any value by default
 *          - with replay operator can emit many previous values
 *          - it does not value property, it can get the value with collecting
 *          - It emits consecutive repeated values also.
 *          - Not similar to LiveData
 */

private val dispatcher = Executors.newSingleThreadExecutor().asCoroutineDispatcher()
private val stateFlow = MutableStateFlow(0)
private val sharedFlow = MutableSharedFlow<Int>(
    replay = 7,
)

private fun setStateFlowValues() {
    stateFlow.value = 1
    stateFlow.value = 2
    stateFlow.value = 3
    stateFlow.value = 4
    stateFlow.value = 5
}

private suspend fun getStateFlowValues() {
    stateFlow.collect {
        println("state flow values $it")
    }
}

private suspend fun setSharedFlowValues() {
    sharedFlow.emit(1)
    sharedFlow.emit(2)
    sharedFlow.emit(2)
    sharedFlow.emit(3)
    sharedFlow.emit(4)
    sharedFlow.emit(5)
    sharedFlow.emit(5)
}

private suspend fun getSharedFlowValues() {
    sharedFlow.collect() {
        println("shared flow values are $it")
    }
}

fun main() {
    GlobalScope.launch(dispatcher) {
        setSharedFlowValues()
        delay(1500)
        getSharedFlowValues() // shared flow shows the repeated consecutive values 1 2 2 3 4 5 5

    }

    setStateFlowValues()
    GlobalScope.launch(dispatcher) {
        getStateFlowValues() // state flow get the last known value which is 5
    }
}