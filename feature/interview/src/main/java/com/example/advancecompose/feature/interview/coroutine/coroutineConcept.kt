package com.example.advancecompose.feature.interview.coroutine

import kotlinx.coroutines.CoroutineScope

/**
 * Async programming
 * 1- parallel operation
 * 2-concurrent operation
 *
 * ** Technologies for concurrent operation
 * A - Thread
 *      - thread is a flow of execution
 *      - one thread can do one task at a time
 *      - when run a java program the main thread will create
 *      - if we want to do a group of tasks, we can use worker threads
 *      ** behind the hoods of threads is drawbacks, it means threads use switching between each other a lot
 *      ** thread switching is heavy and has memory overhead(get memory in the heap)
 *      *** how thread works?
 *          1- thread is a class in the java
 *          2- objects from the thread class get memory allocation in heap and stack
 *          3- call run (start) method
 *          4- jvm communicate with os scheduler to get cpu turn
 * B- AsyncTasks
 * C- RxJava
 * D- Coroutine
 *      - multiple coroutine can run on one thread
 *      - coroutine can run on the thread x and suspend(pause) on it and when resume again can continue on the thread y
 *          it means a coroutine is not bound to particular thread
 *      - computation can be suspended without blocking thread
 *      - when coroutine suspend on a thread save some data and state and when resume again retrieve that data
 *      - coroutine unlike threads don't need a lot of memory
 *      - coroutine builder {
 *                          - launch : first line in the block of launch will execute and the first line after block of launch
 *                          - async
 *                              }
 *      - each suspend function must have the below
 *             1- coroutine scope
 *             2- coroutine context : dispatchers
 *             3- coroutine builders or jobs
 *      - what is suspend
 *             - suspend works without blocking thread because it knows from where to continue after execution
 *             - suspend won't return until the work is started has completed because of continuation object (switch-case)
 *             - for each suspend function the kotlin compiler will create a state machine
 *      - what is coroutine scope
 *             - can control the lifecycle of the coroutines in the particular layer of the app
 *             - when define a coroutine scope it takes a coroutine context as input parameter
 *             - predefined scope lifecycle scope and view model scope
 *      - what is job
 *             - job has lifecycle, cancellation, parent-child relations
 *             - there are two types of jobs
 *                  1- job (like launch)
 *                      - parent job cancel -> all children will be cancel
 *                      - one child fail -> parent job cancel
 *                  1- supervisor job
 *                      - parent job cancel -> all children will be cancel
 *                      - one child fail -> nothing happen
 *      - Structured concurrency (an example of this structure is viewmodel scope)
 *              1- When a scope(view model scope, lifecycle scope) cancels, all of its coroutines cancel.
 *              2- When a suspend fun returns, all of its work is done.
 *              3- When a coroutine errors, its caller or scope is notified.
 *
 * Which parameter is the most important for choosing one of the above ways
 *      - Efficiency means :
 *              - memory overhead
 *              - fewer memory leak
 *              - switching time
 *
 */