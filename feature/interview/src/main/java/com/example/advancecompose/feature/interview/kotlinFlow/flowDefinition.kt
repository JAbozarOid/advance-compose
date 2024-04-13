package com.example.advancecompose.feature.interview.kotlinFlow

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.asCoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.channelFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import java.util.concurrent.Executors

private val dispatcher = Executors.newSingleThreadExecutor().asCoroutineDispatcher()

/**
 * - flow is an asynchronous data stream
 * - flow is an interface with two methods -> emit and collect
 * - flow emits values to the collector
 * - major components of flow as below
 *      - flow builder -> speaker -> emit values
 *      - operator     -> translator ->  transforming the data from one format to another
 *      - collector    -> listener -> collects the items emitted using the flow builder
 */

private suspend fun sampleFlow() {
    flow { // *** flow builder
        (0..10).forEach {
            emit(it)
        }
    }.map {// *** operator
        it * it
    }.collect {// *** collector
        println("$it")
    }
}

fun main() {
    runBlocking(dispatcher) {
        // flow{}
        println("emitted values with flow{} is ${sampleFlow()}")

        // flowOf()
        println(
            "emitted values with flowOf()${
                sampleFlowOf().collect {
                    print(" $it ")
                }
            }"
        )

        // asFlow()
        println(
            "emitted values with asFlow() ${
                sampleAsFlow().collect {
                    print(" $it ")
                }
            }"
        )

        // channelFlow{}
        println(
            "emitted values with channelFlow() ${
                sampleChannelFlow().collect {
                    print(" $it ")
                }
            }"
        )
    }

    GlobalScope.launch(Dispatchers.IO) {
        println(
            "emitted values with flowOn() operator collect on main thread but in this environment I have to set on Dispatchers.IO ${
                sampleFlowOnOperator().collect {
                    print(" $it ")
                }
            }"
        )
    }
}

/**
 * there are 4 types of flow builders
 * 1- flowOf()      : create flow from a given set of items
 * 2- asFlow()      : an extension function to convert type into flows
 * 3- flow{}        : implemented in sampleFlow() method
 * 4- channelFlow{} : create flow with the elements using send provided by the builder itself
 */


// flowOf()
private fun sampleFlowOf(): Flow<Int> {
    // *** emit method is inside of flowOf()
    val flow = flowOf(1, 2, 3, 4).map {
        it * it
    }

    return flow
}

// asFlow()
private fun sampleAsFlow(): Flow<Int> {
    // *** emit method is inside of asFlow()
    val asFlow = (1..5).asFlow().map {
        it * it
    }
    return asFlow
}

// channelFlow{}
private fun sampleChannelFlow() : Flow<Int> {

    val channelFlow = channelFlow {
        (1..20).forEach {
            send(it)
        }
    }
    return channelFlow
}

/**
 * flowOn() : is an operator to control the thread on which the task will be done
 * flowOn() is like subscribeOn() in RxJava
 */
private fun sampleFlowOnOperator() : Flow<Int>{

    // flowOn operator works on flow builder like other operators
    val flow = flow {
        // run on background thread -> Dispatchers.Default
        (1..5).forEach {
            emit(it)
        }
    }.flowOn(Dispatchers.Default)

    return flow
}