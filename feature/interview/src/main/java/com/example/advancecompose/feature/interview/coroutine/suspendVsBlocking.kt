package com.example.advancecompose.feature.interview.coroutine

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.asCoroutineDispatcher
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import java.util.concurrent.Executors

private const val TAG = "test suspending"
private suspend fun timeTakingTask() {
    withContext(Dispatchers.IO) {
        // code for doing a time taking task
        // delay to simulate
        Thread.sleep(5000)
    }
}

// custom dispatcher = it will have only one thread
private val dispatcher = Executors.newSingleThreadExecutor().asCoroutineDispatcher()

private fun testSuspending() {
    GlobalScope.launch(dispatcher) {
        println("suspending call before task 1")
        timeTakingTask() // {
        // when this line of code is executed the dispatcher thread will be free because to do another task
        // in the method timeTakingTask there is another thread which is responsible
        // so the output should be
        // "before task 1"
        // "before task 2"
        // "after task 1" -> continue from the where was suspended
        // "after task 2"
        // }
        println("suspending call after task 1")
    }
    GlobalScope.launch(dispatcher) {
        println("suspending call before task 2")
        timeTakingTask()
        println("suspending call after task 2")
    }
}

private fun testBlocking() {
    GlobalScope.launch(dispatcher) {
        runBlocking {
            println("blocking call before task 1")
            timeTakingTask() //{
            // at this line the dispatcher thread will be block and not allowed to do another task because of runBlocking
            // and it will not be free, it will be block
            // the "before task 1" will be print and after 5 second delay the line
            // "after task 1" will be printed and it is the same for the task 2
            // }
            println("blocking call after task 1")
        }
    }
    GlobalScope.launch(dispatcher) {
        runBlocking {
            println("blocking call before task 2")
            timeTakingTask()
            println("blocking call after task 2")
        }
    }
}


fun main() {
    testSuspending()
    testBlocking()
}