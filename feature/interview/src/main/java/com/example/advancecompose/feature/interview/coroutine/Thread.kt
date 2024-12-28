package com.example.advancecompose.feature.interview.coroutine

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.util.concurrent.atomic.AtomicInteger

/**
 * - number of cores of each processor has increased, and number of the threads each core can have running concurrently has increased
 * - main thread or ui thread is responsible for rendering the ui
 * - worker thread or background thread are not responsible for rendering ui
 * - race condition happened. when two or three threads access to a shared resource simultaneously
 * - shared resources includes database, files, variables
 * - thread safe data structure is a data structure should work correctly even if accessed by multiple threads at the same time
 * - immutable objects are always thread safe
 * - threads usually put data into the queue as objects called messages
 * - the most significant issue of a call-back approach is passing the data from one function to another
 */

@Synchronized
fun increment() : Int{
    var counter = 0

    var plus = AtomicInteger(0) // atomic classes
    plus.incrementAndGet()

    return counter++
}

fun main() {
    println(" thread 1 ${increment()}")
    println(" thread 2 ${increment()}")
    println(" thread 3 ${increment()}")
    println(" thread 4 ${increment()}")

    (1..10000).forEach{
        GlobalScope.launch {
            val threadName = Thread.currentThread().name
            println("$it printed on thread $threadName")
        }
    }
    Thread.sleep(1000)
}