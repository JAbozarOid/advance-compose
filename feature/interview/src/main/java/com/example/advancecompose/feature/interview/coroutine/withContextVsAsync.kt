package com.example.advancecompose.feature.interview.coroutine

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.asCoroutineDispatcher
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.concurrent.Executors

/**
 * withContext does not create a new coroutine, it only shifts the context of the existing coroutine and it's a suspend function
 * withContext can return a result
 * async create a new coroutine and it is not suspend functions
 */

private val dispatcher = Executors.newSingleThreadExecutor().asCoroutineDispatcher()

private suspend fun doLongRunningTask1() : Int {
    val result = withContext(dispatcher) {
        delay(2000)
        return@withContext 10
    }

    return result
}

private suspend fun doLongRunningTask2() : Int {
    return withContext(dispatcher) {
        delay(2000)
        return@withContext 20
    }
}

private suspend fun callWithAsyncParallel() {
    GlobalScope.launch(dispatcher) {
        val deferredTask1 = async { doLongRunningTask1() }
        val task1 = deferredTask1.await()

        val deferredTask2 = async { doLongRunningTask2() }
        val task2 = deferredTask2.await()

        println(task1 + task2)
    }
}



fun main() {
    GlobalScope.launch(dispatcher) {
        //println(doLongRunningTask1() + doLongRunningTask2())

        callWithAsyncParallel()
    }
}