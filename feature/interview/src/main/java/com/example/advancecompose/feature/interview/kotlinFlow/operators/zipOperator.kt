package com.example.advancecompose.feature.interview.kotlinFlow.operators

import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.asCoroutineDispatcher
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.channelFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.zip
import kotlinx.coroutines.launch
import java.util.concurrent.Executors

/**
 * zip operator combine the emission of two flow together and emit single items
 */

private val dispatcher = Executors.newSingleThreadExecutor().asCoroutineDispatcher()

private fun firstFlow(): Flow<Int> {
    return flow {
        (1..5).forEach {
            emit(it)
        }
    }
}

private fun secondFlow(): Flow<String> {
    return flowOf("a", "b", "c", "d", "e")
}

private suspend fun sampleZipOperator() {
    firstFlow().zip(secondFlow()) { intValue, strValue ->
        "$intValue $strValue"
    }.collect {
        println("the result of zip operator is $it")
    }
}


fun main() {
    GlobalScope.launch(dispatcher) {
        sampleZipOperator()

        showResultOfTwoTasks()
    }
}

/**
 * assume we have two long running tasks parallel with different time response
 * we want to show the single result when both of two tasks completed successfully
 */
private suspend fun longRunningTask1(): Flow<String> {
    return channelFlow {
        // call network http
        delay(5000) // result come after 5 seconds
        send("abozar")
    }
}

private fun longRunningTask2(): Flow<String> {
    return flow {
        delay(3000) // result come after 3 seconds
        emit("Raghibdoust")
    }
}

fun showResultOfTwoTasks() {
    GlobalScope.launch(CoroutineExceptionHandler { _, throwable ->
        println("exception ${throwable.message}")
    } + dispatcher) {
        longRunningTask1().zip(longRunningTask2()) { strValue1, strValue2 ->
            return@zip "$strValue1 $strValue2"
        }.catch {
            println("excetion of zip ${it.message}")
        }
            .collect { zipResult ->
                println("the result of two tasks which run parallel is $zipResult")
            }
    }
}