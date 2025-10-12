package com.example.advancecompose.feature.interview.kotlinFlow.operators

import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.asCoroutineDispatcher
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.retry
import kotlinx.coroutines.flow.retryWhen
import kotlinx.coroutines.launch
import java.io.IOException
import java.util.concurrent.Executors

/**
 * retry
 * retryWhen
 * Both operators can be used interchangeably
 */

private val dispatcher = Executors.newSingleThreadExecutor().asCoroutineDispatcher()
private fun longRunningTask(): Flow<Int> {

    return flow {

        delay(2000)
        val num = (0..10).random()
        if (num == 0) {
            throw IOException()
        } else if (num == 1) {
            throw IndexOutOfBoundsException()
        }
        delay(1000)

        emit(1)
    }
}

fun main() {
    GlobalScope.launch(dispatcher + CoroutineExceptionHandler { _, throwable ->
        println("exception in global scope is ${throwable.message}")
    }) {
        longRunningTask().flowOn(Dispatchers.Default).retry(retries = 3) { throwable ->
            if (throwable is IOException) {
                delay(2000)
                return@retry true
            } else {
                return@retry false
            }
        }.catch {
            println("exception catch block ${it.message}")
        }.collect {
            println("the result in retry is $it")
        }
    }

    GlobalScope.launch {
        longRunningTask().retryWhen { cause, attempt ->
            if (cause is IndexOutOfBoundsException && attempt < 3) {
                delay(2000)
                println("attempt is $attempt")
                return@retryWhen true
            } else {
                return@retryWhen false
            }
        }.catch {
            println("the catch block in retry when is ${it.message}")
        }.collect {
            println("the result in retry when is $it")
        }
    }
}

